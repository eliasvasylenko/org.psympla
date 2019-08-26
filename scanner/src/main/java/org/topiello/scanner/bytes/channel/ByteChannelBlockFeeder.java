package org.topiello.scanner.bytes.channel;

import java.nio.channels.ByteChannel;

import org.topiello.scanner.bytes.BlockAllocator;
import org.topiello.scanner.bytes.concurrent.ConcurrentBlockAllocator;
import org.topiello.scanner.bytes.Block;

public class ByteChannelBlockFeeder extends ConcurrentBlockAllocator {
  public static final int DEFAULT_BLOCK_SIZE = 512;

  private final ByteChannel byteChannel;
  private final int blockSize;

  public ByteChannelBlockFeeder(ReadableBytes bytes, int blockSize) {
    this.byteChannel = byteChannel;
    this.blockSize = blockSize;
    this.inputBlock = new Block(this);
  }

  public ByteChannelBlockFeeder(ByteChannel byteChannel) {
    this(byteChannel, DEFAULT_BLOCK_SIZE);
  }

  @Override
  public void awaitAllocation(Block block) {
    if (this.nextBlock == nextBlock) {
      nextBlock.allocateBuffer(buffer);
    }
  }

  @Override
  public int awaitData(Block block, int limit) {
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
  public void release(Block block) {
    // TODO Auto-generated method stub

  }
}
