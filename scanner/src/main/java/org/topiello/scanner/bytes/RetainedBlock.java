package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.Optional;

public final class RetainedBlock {
  private final long startPosition;
  private final ByteBuffer buffer;

  private RetainedBlock next;

  public RetainedBlock(long startPosition, ByteBuffer buffer, Runnable onClose) {
    this.startPosition = startPosition;
    this.buffer = buffer;
  }

  public long position() {
    return startPosition + buffer.position();
  }

  public long available() {
    return buffer.remaining();
  }

  public long endPosition() {
    return startPosition + buffer.limit();
  }

  public byte advance() {
    return buffer.get();
  }

  public byte peek() {
    return buffer.get(buffer.position());
  }

  public byte get(long index) {
    // TODO if index is beyond the end we just recurse into the next block!
    return buffer.get((int) index);
  }

  public RetainedBlock next() {
    return next;
  }

  public void close() {
    // TODO
  }
}
