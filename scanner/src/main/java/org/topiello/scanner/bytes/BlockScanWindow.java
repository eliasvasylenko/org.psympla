package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.topiello.scanner.ScanWindow;
import org.topiello.scanner.Scanner;
import org.topiello.scanner.ScannerClosedException;

public class BlockScanWindow implements ScanWindow<Byte, ByteBuffer> {
  private final BlockScanner scanner;
  private ByteBlock inputBlock;
  private ByteBuffer buffer;

  private BlockScanWindow(BlockScanner scanner) {
    this.scanner = scanner;
    this.inputBlock = scanner.inputBlock;
    this.buffer = scanner.buffer == null
        ? null
        : this.buffer.duplicate().limit(this.buffer.position());
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
  public long retainedPosition() {
    if (inputBlock == null) {
      throw new ScannerClosedException();
    } else if (buffer == null) {
      return inputBlock.startPosition();
    } else {
      return inputBlock.startPosition() + buffer.position();
    }
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
    if (position > blockScanner.inputPosition() || position < retainedPosition()) {
      throw new IndexOutOfBoundsException(Long.toString(position));
    }
    while (position >= retainedBlock.endPosition()) {
      retainedBlock.close();
      retainedBlock = retainedBlock.next();
    }
  }

  @Override
  public ByteBuffer takeToPosition(long position) {
    if (position > blockScanner.inputPosition()) {
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