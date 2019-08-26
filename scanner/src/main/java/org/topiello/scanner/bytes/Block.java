package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Block {
  private final BlockFeeder feeder;
  private final long startPosition;
  private ByteBuffer buffer;

  private final CountDownLatch bufferAllocationLatch = new CountDownLatch(1);
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
      try {
        bufferAllocationLatch.await();
      } catch (InterruptedException e) {
        throw new ScannerInterruptedException(e);
      }
      feeder.allocateBlock(this);
    }
  }

  int readyBuffer(int limit) {
    if (buffer.position() >= limit) {
      return buffer.position();
    }
    try {
      bufferAllocationLatch.await();
    } catch (InterruptedException e) {
      throw new ScannerInterruptedException(e);
    }
    return feeder.fillBlock(this, limit);
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
