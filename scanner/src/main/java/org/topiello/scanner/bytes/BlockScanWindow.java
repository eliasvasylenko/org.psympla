package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.topiello.scanner.Cursor;
import org.topiello.scanner.InputPositionOutOfBoundsException;
import org.topiello.scanner.ScanWindow;
import org.topiello.scanner.Scanner;
import org.topiello.scanner.ScannerClosedException;

public class BlockScanWindow implements ScanWindow<Byte, ByteBuffer> {
  private final BlockScanner scanner;
  private Block block;
  private ByteBuffer buffer;

  BlockScanWindow(BlockScanner scanner) {
    this.scanner = scanner;
    this.block = scanner.block();
    this.buffer = scanner.buffer() == null
        ? null
        : scanner.buffer().duplicate().limit(scanner.buffer().position());
    block.openWindow();
  }

  @Override
  public void close() {
    if (block != null) {
      block.closeWindow();
      block = null;
      buffer = null;
    }
  }

  @Override
  public long retainedPosition() {
    if (block == null) {
      throw new ScannerClosedException();
    } else if (buffer == null) {
      return block.startPosition();
    } else {
      return block.startPosition() + buffer.position();
    }
  }

  @Override
  public Stream<Byte> streamInterval(long fromPosition, long toPosition) {
    return LongStream.range(fromPosition, toPosition).mapToObj(i -> block.get(i));
  }

  @Override
  public Scanner<Byte, ByteBuffer> scanner() {
    return scanner;
  }

  /*
   * TODO this should be much simpler to implement than the equivalent "advanceTo"
   * in the scanner, because it does not need to be synchronized with advancing of
   * the scanner. Rather than blocking to wait for availability it just fails if
   * not enough is available, so we don't have to propogate any requests up to the
   * feeder.
   */

  @Override
  public void discardTo(long position) {
    var delta = (int) (position - retainedPosition());
    if (delta < 0) {
      throw new InputPositionOutOfBoundsException(position);
    }

    while (prepareRead()) {
      delta = delta - buffer.remaining();
      if (delta < 0) {
        buffer.position(buffer.limit() + delta);
        completeRead();
        return peek();
      }
      buffer.position(buffer.limit());
      completeRead();
    }

    return new Cursor<>();
  }

  @Override
  public ByteBuffer takeTo(long position) {
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