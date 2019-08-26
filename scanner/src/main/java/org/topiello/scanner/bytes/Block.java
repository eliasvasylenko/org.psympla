package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public class Block {
  private final BlockAllocator feeder;
  private final long startPosition;
  private ByteBuffer buffer;

  private final AtomicInteger totalCount;
  private volatile Block next;

  public Block(BlockAllocator feeder) {
    this.feeder = feeder;
    this.startPosition = 0;
    this.totalCount = new AtomicInteger(0);
  }

  private Block(BlockAllocator feeder, long startPosition) {
    this.feeder = feeder;
    this.startPosition = startPosition;
    this.totalCount = new AtomicInteger(1);
  }

  public long startPosition() {
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
      feeder.release(this);
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
      feeder.awaitAllocation(this);
    }
  }

  int readyBuffer(int limit) {
    if (buffer.position() < limit) {
      feeder.awaitData(this, limit);
    }
    return buffer.position();
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
