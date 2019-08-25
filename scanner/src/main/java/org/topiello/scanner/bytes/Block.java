package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public class Block {
  private final BlockFeeder feeder;
  private final long startPosition;
  private ByteBuffer buffer;

  private final AtomicInteger totalCount;
  private volatile Block next;

  public Block(BlockFeeder feeder) {
    this.feeder = feeder;
    this.startPosition = 0;
    this.totalCount = new AtomicInteger(0);
  }

  Block(BlockFeeder feeder, long startPosition) {
    this.feeder = feeder;
    this.startPosition = startPosition;
    this.totalCount = new AtomicInteger(1);
  }

  long startPosition() {
    return startPosition;
  }

  int bufferLimit() {
    return buffer != null ? buffer.position() : 0;
  }

  void open() {
    totalCount.incrementAndGet();
  }

  void close() {
    if (totalCount.decrementAndGet() == 0) {
      feeder.close(this);
      if (next != null) {
        next.close();
      } else {
        feeder.close();
      }
    }
  }

  Block next() {
    next.open();
    close();
    return next;
  }

  void allocateBuffer() {
    if (buffer == null) {
      if (scannerCount.get() == 1) {
        feeder.allocateBlock(this);
        /*
         * TODO instead of synchronize, use locks
         * 
         * 
         * 
         * TODO is there any value in the scannerCount == 1 fast-path? We can avoid a
         * synchronize in the case that we're doing the reading on the calling thread,
         * but that's a slow way to do it anyway. Assuming reading is being done on
         * another thread / fiber and we just have to wait for it, do we save time this
         * way? no probably not. Let's assume we're doing it on another thread/fiber and
         * single-threaded mode is done by using a single threaded executor with fibers.
         * single-threaded mode is done by using a single threaded executor with fibers.
         */
      } else {
        synchronized (this) {
          feeder.allocateBlock(this);
        }
      }
    }
  }

  int readyBuffer(int limit) {
    if (buffer.position() >= limit) {
      return buffer.position();
    }

    if (scannerCount.get() == 1) {
      return feeder.fillBlock(this, limit);
    } else {
      synchronized (this) {
        return feeder.fillBlock(this, limit);
      }
    }
  }

  ByteBuffer getReadBuffer() {
    return buffer == null ? null : buffer.duplicate().flip();
  }

  public Block allocateBuffer(ByteBuffer buffer) {
    this.buffer = buffer.asReadOnlyBuffer();
    this.next = new Block(feeder, startPosition + buffer.capacity());
    return next;
  }
}
