package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import org.topiello.scanner.ScannerClosedException;
import org.topiello.scanner.ScannerFailedException;

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
    context.open(this);
  }

  private Block(BlockContext context, long startPosition) {
    this.context = context;
    this.startPosition = startPosition;
    this.referenceCount = new AtomicInteger(1);
  }

  public long startPosition() {
    return startPosition;
  }

  public ByteBuffer getByteBuffer() {
    return buffer;
  }

  void acquire() {
    referenceCount.incrementAndGet();
  }

  void release() {
    int references = referenceCount.decrementAndGet();

    if (references < 0) {
      referenceCount.incrementAndGet();
      throw new ScannerClosedException();

    } else if (references == 0) {
      context.release(this);
      if (next != null) {
        next.release();
      } else {
        context.close();
      }
    }
  }

  Block next() {
    if (next == null) {
      throw new ScannerFailedException("Next block is not available");
    }
    next.acquire();
    release();
    return next;
  }

  void awaitAllocation() {
    if (buffer == null) {
      context.awaitAllocation(this);
    }
  }

  int awaitData(int limit) {
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
