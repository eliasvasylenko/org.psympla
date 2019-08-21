package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.topiello.scanner.Cursor;
import org.topiello.scanner.InputPositionOutOfBoundsException;
import org.topiello.scanner.ScanWindow;
import org.topiello.scanner.Scanner;
import org.topiello.scanner.ScannerClosedException;

public class BlockScanner implements Scanner<Byte, ByteBuffer> {
  private InputBlock inputBlock;
  private ByteBuffer buffer;
  private volatile long inputPosition;

  public BlockScanner(BlockFeeder feeder) {
    this.inputBlock = feeder.open();
    this.buffer = inputBlock.getReadBuffer();
    this.inputPosition = inputBlock.startPosition() + (buffer != null ? buffer.position() : 0);
    inputBlock.open();
  }

  private BlockScanner(InputBlock inputBlock, ByteBuffer buffer, long inputPosition) {
    this.inputBlock = inputBlock;
    this.buffer = buffer;
    this.inputPosition = inputPosition;
    inputBlock.open();
  }

  @Override
  public void close() {
    if (inputBlock != null) {
      /*
       * 
       * 
       * TODO input block set to null .... currently still used by retained buffer
       * window ...
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       * 
       */
      inputBlock.close();
      inputBlock = null;
      buffer = null;
    }
  }

  @Override
  public long inputPosition() {
    return inputPosition;
  }

  @Override
  public Cursor<Byte> peek() {
    if (prepareRead()) {
      return new Cursor<>(buffer.get(buffer.position()));
    } else {
      return new Cursor<>();
    }
  }

  @Override
  public Cursor<Byte> advance() {
    if (prepareRead()) {
      var cursor = new Cursor<>(buffer.get());
      completeRead();
      return cursor;
    } else {
      return new Cursor<>();
    }
  }

  @Override
  public Cursor<Byte> advanceWhile(Predicate<Byte> condition) {
    byte next = 0;
    while (prepareRead()) {
      long run = buffer.remaining();
      for (long i = 0; i < run; i++) {
        next = buffer.get();
        if (!condition.test(next)) {
          int bufferPosition = buffer.position() - 1;
          completeRead(bufferPosition);
          return new Cursor<>(next);
        }
      }
      completeRead();
    }
    return new Cursor<>();
  }

  @Override
  public Cursor<Byte> advanceTo(long inputPosition) {
    if (inputPosition < this.inputPosition) {
      throw new InputPositionOutOfBoundsException(inputPosition);
    }

    while (prepareRead()) {
      int bufferPosition = (int) (inputPosition - inputBlock.startPosition());
      if (bufferPosition < buffer.limit()) {
        completeRead(bufferPosition);
        return peek();
      }
      completeRead(buffer.limit());
    }

    if (inputPosition > this.inputPosition) {
      throw new InputPositionOutOfBoundsException(inputPosition);
    }

    return new Cursor<>();
  }

  private boolean prepareRead() {
    if (buffer != null) {
      if (buffer.hasRemaining()) {
        return true;
      }
      inputBlock.readyBuffer(buffer.position());
      buffer.limit(inputBlock.bufferLimit());

    } else if (inputBlock != null) {
      inputBlock.allocateBuffer();
      inputBlock.readyBuffer(0);
      buffer = inputBlock.getReadBuffer();

    } else {
      throw new ScannerClosedException();
    }

    if (buffer.hasRemaining()) {
      return true;
    } else {
      close();
      return false;
    }
  }

  private void completeRead() {
    int bufferPosition = buffer.position();
    inputPosition = inputBlock.startPosition() + bufferPosition;
    if (bufferPosition == buffer.capacity()) {
      inputBlock = inputBlock.next();
      buffer = inputBlock.getReadBuffer();
    }
  }

  private void completeRead(int bufferPosition) {
    buffer.position(bufferPosition);
    completeRead();
  }

  @Override
  public Scanner<Byte, ByteBuffer> branch() {
    var buffer = this.buffer == null ? null : this.buffer.duplicate();
    return new BlockScanner(inputBlock, buffer, inputPosition);
  }

  @Override
  public ScanWindow<Byte, ByteBuffer> openWindow() {
    var buffer = this.buffer == null ? null : this.buffer.duplicate().limit(this.buffer.position());
    return new BlockScanWindow(buffer, inputPosition);
  }

  public class BlockScanWindow implements ScanWindow<Byte, ByteBuffer> {
    private ByteBuffer buffer;
    private long retainedPosition;

    public BlockScanWindow(ByteBuffer buffer, long retainedPosition) {
      this.buffer = buffer;
      this.retainedPosition = retainedPosition;
      inputBlock.open();
    }

    @Override
    public void close() {
      inputBlock.close();
    }

    @Override
    public long retainedPosition() {
      return retainedBlock.position();
    }

    @Override
    public Stream<Byte> streamPositionInterval(long fromPosition, long toPosition) {
      return LongStream.range(fromPosition, toPosition).mapToObj(i -> retainedBlock.get(i));
    }

    @Override
    public Scanner<Byte, ByteBuffer> scanner() {
      return BlockScanner.this;
    }

    @Override
    public void discardToPosition(long position) {
      if (position > inputPosition() || position < retainedPosition()) {
        throw new IndexOutOfBoundsException(Long.toString(position));
      }
      while (position >= retainedBlock.endPosition()) {
        retainedBlock.close();
        retainedBlock = retainedBlock.next();
      }
    }

    @Override
    public ByteBuffer takeToPosition(long position) {
      if (position > inputPosition()) {
        throw new IndexOutOfBoundsException(Long.toString(position));
      }
      int size = (int) (position - retainedPosition());
      if (size < 0) {
        throw new IndexOutOfBoundsException(Long.toString(position));
      }
      ByteBuffer destination = ByteBuffer.allocate(size);
      while (position >= retainedBlock.endPosition()) {
        retainedBlock.close();
        retainedBlock = retainedBlock.next();
      }
    }
  }
}
