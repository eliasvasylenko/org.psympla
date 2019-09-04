package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.function.Predicate;

import org.topiello.scanner.Cursor;
import org.topiello.scanner.ScanWindow;
import org.topiello.scanner.Scanner;
import org.topiello.scanner.ScannerClosedException;
import org.topiello.scanner.ScannerPositionOutOfBoundsException;

public class BlockScanner implements Scanner<Byte, ByteBuffer> {
  private Block block;
  private ByteBuffer buffer;

  public BlockScanner(BlockContext context) {
    this(new Block(context));
  }

  protected BlockScanner(Block block) {
    this.block = block;
    this.buffer = block.getReadBuffer();
    this.block.acquire();
  }

  private BlockScanner(BlockScanner scanner) {
    this.block = scanner.block;
    this.buffer = scanner.buffer == null ? null : scanner.buffer.duplicate();
    this.block.acquire();
  }

  @Override
  public Scanner<Byte, ByteBuffer> branch() {
    return new BlockScanner(this);
  }

  @Override
  public ScanWindow<Byte, ByteBuffer> openWindow() {
    return new BlockScanWindow(this);
  }

  public ByteBuffer buffer() {
    return buffer;
  }

  public Block block() {
    return block;
  }

  public boolean isOpen() {
    return block != null;
  }

  @Override
  public void close() {
    if (isOpen()) {
      block.release();
      block = null;
      buffer = null;
    }
  }

  @Override
  public long inputPosition() {
    if (!isOpen()) {
      throw new ScannerClosedException();

    } else if (buffer == null) {
      return block.startPosition();

    } else {
      return block.startPosition() + buffer.position();
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
          int bufferPosition = buffer.position() - 1;
          buffer.position(bufferPosition);
          completeRead();
          return new Cursor<>(next);
        }
      }
      completeRead();
    }
    return new Cursor<>();
  }

  @Override
  public Cursor<Byte> advanceTo(long position) {
    var delta = (int) (position - inputPosition());
    if (delta < 0) {
      throw new ScannerPositionOutOfBoundsException(position);
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

  private boolean prepareRead() {
    if (buffer != null) {
      if (buffer.hasRemaining()) {
        return true;
      }
      block.awaitData(buffer.position());
      buffer.limit(block.getByteBuffer().position());

    } else if (block != null) {
      block.awaitAllocation();
      block.awaitData(0);
      buffer = block.getReadBuffer();

    } else {
      return false;
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
    if (bufferPosition == buffer.capacity()) {
      block = block.next();
      buffer = block.getReadBuffer();
    }
  }
}
