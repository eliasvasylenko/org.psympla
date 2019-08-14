package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public class InputBlock {
  private final BlockFeeder feeder;
  private final long startPosition;
  private ByteBuffer buffer;

  private AtomicInteger referenceCount;
  private volatile InputBlock next;

  public InputBlock(BlockFeeder feeder) {
    this.feeder = feeder;
    this.startPosition = 0;
    this.referenceCount = new AtomicInteger(0);
  }

  InputBlock(BlockFeeder feeder, long startPosition) {
    this.feeder = feeder;
    this.startPosition = startPosition;
    this.referenceCount = new AtomicInteger(1);
  }

  public long startPosition() {
    return startPosition;
  }

  public long endPosition() {
    return startPosition + buffer.capacity();
  }

  public int readyPosition() {
    return buffer.limit();
  }

  void open() {
    referenceCount.incrementAndGet();
  }

  void close() {
    if (referenceCount.decrementAndGet() == 0) {
      feeder.close(this);
      if (next != null) {
        next.close();
      }
    }
  }

  InputBlock next() {
    next.open();
    close();
    return next;
  }

  void readyBuffer(int limit) {
    if (buffer == null) {
      if (referenceCount.get() == 1) {
        feeder.fillBlock(this, limit);
      } else {
        synchronized (this) {
          feeder.fillBlock(this, limit);
        }
      }
    }
  }

  ByteBuffer getReadBuffer() {
    return buffer == null ? null : buffer.duplicate().flip();
  }

  public void allocateBuffer(ByteBuffer buffer) {
    this.buffer = buffer.asReadOnlyBuffer();
    this.next = new InputBlock(feeder, startPosition + buffer.capacity());
  }
}
