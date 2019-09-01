package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

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
    block.open();
  }

  BlockScanWindow(BlockScanWindow scanWindow) {
    this.scanner = scanWindow.scanner;
    this.block = scanWindow.block;
    this.buffer = scanWindow.buffer == null ? null : scanWindow.buffer.duplicate();
    block.open();
  }

  @Override
  public ScanWindow<Byte, ByteBuffer> branch() {
    return new BlockScanWindow(this);
  }

  @Override
  public Scanner<Byte, ByteBuffer> scanner() {
    return scanner;
  }

  @Override
  public void close() {
    if (block != null) {
      block.close();
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

  private int getDelta(long position) {
    int delta = (int) (position - retainedPosition());
    if (delta < 0 || position > scanner().inputPosition()) {
      throw new InputPositionOutOfBoundsException(position);
    }
    return delta;
  }

  @Override
  public Stream<Byte> stream() {
    return Stream.<Optional<Stream<Byte>>>generate(() -> {
      if (buffer == null) {
        buffer = block.getReadBuffer();
      } else if (buffer.position() == buffer.capacity()) {
        block = block.next();
        buffer = block.getReadBuffer();
      } else {
        buffer.limit(block.getByteBuffer().position());
      }
      if (buffer.hasRemaining()) {
        return Optional.of(Stream.generate(buffer::get).limit(buffer.remaining()));
      } else {
        return Optional.empty();
      }
    })
        .takeWhile(o -> o.isPresent())
        .flatMap(Optional::stream)
        .flatMap(Function.identity())
        .sequential();
  }

  @Override
  public void discardTo(long position) {
    var delta = getDelta(position);

    if (buffer == null) {
      buffer = block.getReadBuffer();
    } else {
      buffer.limit(block.getByteBuffer().position());
    }

    while (delta > buffer.capacity()) {
      delta -= buffer.capacity();
      block = block.next();
      buffer = block.getReadBuffer();
    }

    buffer.position(delta);
  }

  @Override
  public ByteBuffer takeTo(long position) {
    var delta = getDelta(position);

    if (buffer == null) {
      buffer = block.getReadBuffer();
    } else {
      buffer.limit(block.getByteBuffer().position());
    }

    ByteBuffer destination = ByteBuffer.allocate(delta);

    while (delta > buffer.capacity()) {
      destination.put(buffer);
      delta -= buffer.capacity();
      block = block.next();
      buffer = block.getReadBuffer();
    }

    int limit = buffer.limit();
    buffer.limit(destination.remaining());
    destination.put(buffer);
    buffer.limit(limit);

    return destination;
  }
}