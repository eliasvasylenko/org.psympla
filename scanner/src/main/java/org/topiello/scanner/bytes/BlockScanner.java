package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.topiello.scanner.Cursor;
import org.topiello.scanner.ScanWindow;
import org.topiello.scanner.Scanner;
import org.topiello.scanner.ScannerClosedException;

public class BlockScanner implements Scanner<Byte, ByteBuffer> {
  private InputBlock inputBlock;
  private ByteBuffer buffer;

  public BlockScanner(BlockFeeder feeder) {
    this.inputBlock = feeder.open();
  }

  public BlockScanner(BlockScanner blockScanner) {
    this.inputBlock = blockScanner.inputBlock;
    this.buffer = blockScanner.buffer.duplicate();
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
    if (buffer != null) {
      return inputBlock.startPosition() + buffer.position();
    } else if (inputBlock != null) {
      return inputBlock.startPosition();
    } else {
      throw new ScannerClosedException();
    }
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
          buffer.position(buffer.position() - 1);
          return new Cursor<>(next);
        }
      }
      completeRead();
    }
    return new Cursor<>();
  }

  @Override
  public Cursor<Byte> advanceTo(long inputPosition) {
    // TODO Auto-generated method stub
    return Scanner.super.advanceTo(inputPosition);
  }

  /**
   * Check the buffer before we read from it to see if it can be prepared for
   * reading.
   * <p>
   * This methodd must return false if the scanner is at the end of the input.
   * Otherwise it must return true, and prepare the buffer for reading, blocking
   * to wait for input if necessary,
   * 
   * @return true if the buffer has been prepared for reading, false if the input
   *         is complete
   */
  private boolean prepareRead() {
    buffer = inputBlock.prepareBuffer(buffer);
    return buffer != null;
  }

  private void prepareBuffer() {
    if (buffer == null) {
      buffer = inputBlock.getBuffer().duplicate().flip();
    } else if (buffer.hasRemaining()) {
      return;
    }

    buffer.limit(inputBlock.bufferLimit());
    if (buffer.hasRemaining()) {
      return;
    }
    // TODO feeder.advance(inputPosition());

    buffer = inputBlock.getBuffer();

    if (this.buffer.position() > 0) {
      return this.buffer.duplicate().flip();
    }
    // TODO Auto-generated method stub
    return null;
  }

  private void completeRead() {
    if (buffer.position() == buffer.capacity()) {
      inputBlock = inputBlock.nextBlock();
      buffer = inputBlock.getBuffer();
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
