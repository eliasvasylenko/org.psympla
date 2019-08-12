package org.topiello.scanner.bytes.channel;

import java.nio.channels.ByteChannel;

import org.topiello.scanner.bytes.BlockFeeder;
import org.topiello.scanner.bytes.InputBlock;

public class ByteChannelBlockFeeder implements BlockFeeder {
  public static final int DEFAULT_BLOCK_SIZE = 512;

  private final ByteChannel byteChannel;
  private final int blockSize;

  private InputBlock inputBlock;

  public ByteChannelBlockFeeder(ByteChannel byteChannel, int blockSize) {
    this.byteChannel = byteChannel;
    this.blockSize = blockSize;
    this.inputBlock = new InputBlock(this);
  }

  public ByteChannelBlockFeeder(ByteChannel byteChannel) {
    this(byteChannel, DEFAULT_BLOCK_SIZE);
  }

  /**
   * Advance up to at least the given input position before returning.
   */
  @Override
  public void advance(long inputPosition) {
    // TODO Auto-generated method stub

  }

  @Override
  public InputBlock open() {
    return inputBlock;
  }

  @Override
  public void close(InputBlock block) {
    // TODO Auto-generated method stub

  }
}
