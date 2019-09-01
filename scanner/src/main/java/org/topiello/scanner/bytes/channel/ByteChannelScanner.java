package org.topiello.scanner.bytes.channel;

import org.topiello.scanner.bytes.BlockScanner;

public class ByteChannelScanner extends BlockScanner {
  private final ByteChannelBlockContext blockContext;

  protected ByteChannelScanner(ByteChannelBlockContext blockContext) {
    super(blockContext);
    this.blockContext = blockContext;
  }

  public ByteChannelScanner(ReadableBytes bytes, ByteBufferCache bufferCache) {
    this(new ByteChannelBlockContext(bytes, bufferCache));
  }

  public ByteChannelScanner(ReadableBytes bytes) {
    this(bytes, ByteBufferCache.createPrivate());
  }

  public void feed() {
    blockContext.feed();
  }
}
