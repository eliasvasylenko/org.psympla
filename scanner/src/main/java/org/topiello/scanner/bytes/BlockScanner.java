package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.topiello.scanner.Cursor;
import org.topiello.scanner.EndOfInputException;
import org.topiello.scanner.InputPositionOutOfBoundsException;
import org.topiello.scanner.ScanWindow;
import org.topiello.scanner.Scanner;

public class BlockScanner implements Scanner<Byte, ByteBuffer> {
  private InputBlock inputBlock;
  private ByteBuffer buffer;
  private long inputPosition;

  public BlockScanner(BlockFeeder feeder) {
    this.inputBlock = feeder.open();
    this.buffer = inputBlock.getReadBuffer();
    this.inputPosition = inputBlock.startPosition() + (buffer != null ? buffer.position() : 0);
    inputBlock.open();
  }

  public BlockScanner(BlockScanner blockScanner) {
    this.inputBlock = blockScanner.inputBlock;
    this.inputPosition = blockScanner.inputPosition;
    this.buffer = blockScanner.buffer == null ? null : blockScanner.buffer.duplicate();
    inputBlock.open();
  }

  @Override
  public void close() {
    if (inputBlock != null) {
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
      inputPosition++;
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
          buffer.position(bufferPosition);
          inputPosition = inputBlock.startPosition() + bufferPosition;
          return new Cursor<>(next);
        }
      }
      inputPosition += run;
      completeRead();
    }
    return new Cursor<>();
  }

  @Override
  public Cursor<Byte> advanceTo(long inputPosition) {
    if (prepareRead(inputPosition)) {
      return new Cursor<>(buffer.get(buffer.position()));
    } else {
      return new Cursor<>();
    }
  }

  private boolean prepareRead(long inputPosition) {
    int delta = (int) (inputPosition - this.inputPosition);
    if (delta < 0) {
      throw new InputPositionOutOfBoundsException(inputPosition);
    }
    if (buffer != null) {
      if (delta < buffer.remaining()) {
        buffer.position(buffer.position() + delta);
        return true;
      }
    } else {
      inputBlock.readyBuffer(0);
    }
    while (inputBlock.endPosition() >= inputPosition) {
      inputBlock = inputBlock.next();
      inputBlock.readyBuffer(0);
    }

    buffer = inputBlock.getReadBuffer();

    // TODO correct to here
    
    long preparedPosition = inputBlock.prepareTo(inputPosition);
    if (preparedPosition < inputPosition) {
      throw new EndOfInputException(inputPosition);
    } else if (preparedPosition == inputPosition) {
      return false;
    }
    this.inputPosition = inputPosition;
  }

  private boolean prepareRead() {
    if (buffer != null) {
      if (buffer.hasRemaining()) {
        return true;
      }
      inputBlock.readyBuffer(buffer.position() + 1);
      buffer.limit(inputBlock.readyPosition());
    } else {
      inputBlock.readyBuffer(1);
      buffer = inputBlock.getReadBuffer();
    }
    if (buffer.hasRemaining()) {
      return true;
    } else {
      close();
      return false;
    }
  }

  private void completeRead() {
    if (buffer.position() == buffer.capacity()) {
      inputBlock = inputBlock.next();
      buffer = inputBlock.getReadBuffer();
    }
  }

  @Override
  public Scanner<Byte, ByteBuffer> branch() {
    return new BlockScanner(this);
  }

  @Override
  public ScanWindow<Byte, ByteBuffer> openWindow() {
    return new BlockScanWindow();
  }

  public class BlockScanWindow implements ScanWindow<Byte, ByteBuffer> {
    private ByteBuffer buffer;
    private RetainedBlock retainedBlock;

    public BlockScanWindow() {
      // TODO Auto-generated constructor stub
    }

    @Override
    public void close() {
      // TODO Auto-generated method stub

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
