package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public class Block {
  private final BlockContext context;
  private final long startPosition;
  private ByteBuffer buffer;

  private final AtomicInteger referenceCount;
  private volatile Block next;

  Block(BlockContext context) {
    this.context = context;
    this.startPosition = 0;
    this.referenceCount = new AtomicInteger(0);
  }

  private Block(BlockContext context, long startPosition) {
    this.context = context;
    this.startPosition = startPosition;
    this.referenceCount = new AtomicInteger(1);
  }

  public long startPosition() {
    return startPosition;
  }

  int bufferLimit() {
    return buffer != null ? buffer.position() : 0;
  }

  void open() {
    referenceCount.incrementAndGet();
  }

  void close() {
    if (referenceCount.decrementAndGet() <= 0) {
      context.release(this);
      if (next != null) {
        next.close();
      } else {
        context.close();
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
      context.awaitAllocation(this);
    }
  }

  int readyBuffer(int limit) {
    if (buffer.position() < limit) {
      context.awaitData(this, limit);
    }
    return buffer.position();
  }

  ByteBuffer getReadBuffer() {
    return buffer == null ? null : buffer.duplicate().flip();
  }

  public Block allocateBuffer(ByteBuffer buffer) {
    this.buffer = buffer.asReadOnlyBuffer();
    this.next = new Block(context, startPosition + buffer.capacity());
    return next;
  }
}
