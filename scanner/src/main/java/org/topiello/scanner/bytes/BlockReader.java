package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;

import org.topiello.scanner.ScannerClosedException;

public class BlockReader {
  private ByteBlock byteBlock;
  private ByteBuffer buffer;

  protected BlockReader(ByteBlock byteBlock, ByteBuffer buffer) {
    this.byteBlock = byteBlock;
    this.buffer = buffer;
    byteBlock.open();
  }

  protected final ByteBlock byteBlock() {
    return byteBlock;
  }

  protected final void byteBlock(ByteBlock byteBlock) {
    this.byteBlock = byteBlock;
  }

  protected final ByteBuffer buffer() {
    return buffer;
  }

  protected final void buffer(ByteBuffer buffer) {
    this.buffer = buffer;
  }

  protected final void close() {
    if (byteBlock != null) {
      byteBlock.close();
      byteBlock = null;
      buffer = null;
    }
  }

  public final long position() {
    if (byteBlock == null) {
      throw new ScannerClosedException();

    } else if (buffer == null) {
      return byteBlock.startPosition();

    } else {
      return byteBlock.startPosition() + buffer.position();
    }
  }
}
