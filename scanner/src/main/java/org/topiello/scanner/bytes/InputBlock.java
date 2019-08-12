package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;

public class InputBlock {
  private final BlockFeeder feeder;
  private final long startPosition;
  private ByteBuffer buffer;

  private volatile boolean isLast;
  private volatile int referenceCount = 1;
  private volatile InputBlock next;

  public InputBlock(BlockFeeder feeder) {
    this.feeder = feeder;
    this.startPosition = 0;
    this.isLast = true;
  }

  InputBlock(BlockFeeder feeder, long startPosition) {
    this.feeder = feeder;
    this.startPosition = startPosition;
    this.isLast = false;
  }

  long startPosition() {
    return startPosition;
  }

  void open() {
    referenceCount++;
  }

  void close() {
    if (--referenceCount == 0 && isLast) {
      feeder.close(this);
      next.setLast();
    }
  }

  private void setLast() {
    isLast = true;
    if (referenceCount == 0) {
      feeder.close(this);
      next.setLast();
    }
  }

  int bufferLimit() {
    return this.buffer.position();
  }

  ByteBuffer prepareBuffer(ByteBuffer buffer) {
    if (buffer != null) {
      if (buffer.hasRemaining()) {
        return buffer;
      }
      if (this.buffer.position() == buffer.limit()
          && !feeder.advance(startPosition + buffer.limit())) {
        return null;
      }
      buffer.limit(this.buffer.position());
      return buffer;
    }
    if (this.buffer == null) {

    }
    if (this.buffer.position() > 0) {
      return this.buffer.duplicate().flip();
    }
    // TODO Auto-generated method stub
    return null;
  }

  public ByteBuffer getBuffer() {
    return buffer;
  }

  InputBlock nextBlock() {
    return next;
  }

  public void allocateBuffer(ByteBuffer buffer) {
    this.buffer = buffer.asReadOnlyBuffer();
    this.next = new InputBlock(feeder, startPosition + buffer.capacity());
  }
}
