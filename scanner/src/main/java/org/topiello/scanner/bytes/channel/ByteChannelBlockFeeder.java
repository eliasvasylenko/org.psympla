package org.topiello.scanner.bytes.channel;

import java.nio.channels.ByteChannel;

import org.topiello.scanner.bytes.BlockFeeder;
import org.topiello.scanner.bytes.Block;

public class ByteChannelBlockFeeder implements BlockFeeder {
  public static final int DEFAULT_BLOCK_SIZE = 512;

  private final ByteChannel byteChannel;
  private final int blockSize;

  private volatile long inputPosition;
  private Block inputBlock;
  private Block nextBlock;

  public ByteChannelBlockFeeder(ByteChannel byteChannel, int blockSize) {
    this.byteChannel = byteChannel;
    this.blockSize = blockSize;
    this.inputBlock = new Block(this);
  }

  public ByteChannelBlockFeeder(ByteChannel byteChannel) {
    this(byteChannel, DEFAULT_BLOCK_SIZE);
  }

  @Override
  public void allocateBlock(Block nextBlock) {
    if (this.nextBlock == nextBlock) {
      nextBlock.allocateBuffer(buffer);
    }
  }

  @Override
  public int fillBlock(Block block, int limit) {
    if (inputBlock != block) {
      return blockSize;
    }
    synchronized (this) {
      while (inputPosition >= this.inputPosition) {
        try {
          wait();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
    // TODO Auto-generated method stub
    return this.inputPosition;
  }

  @Override
  public Block open() {
    return inputBlock;
  }

  @Override
  public void close(Block block) {
    // TODO Auto-generated method stub

  }
}
