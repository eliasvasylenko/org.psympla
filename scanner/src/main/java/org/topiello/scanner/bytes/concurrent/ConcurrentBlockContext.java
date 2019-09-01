package org.topiello.scanner.bytes.concurrent;

import java.nio.ByteBuffer;

import org.topiello.scanner.bytes.Block;
import org.topiello.scanner.bytes.BlockContext;

public abstract class ConcurrentBlockContext implements BlockContext {
  private volatile ConcurrentBlock inputBlock;

  @Override
  public void awaitAllocation(Block block) {
    inputBlock.awaitAllocation(block);
  }

  @Override
  public void awaitData(Block block, int limit) {
    inputBlock.awaitData(block, limit);
  }

  @Override
  public void open(Block initialBlock) {
    inputBlock = new ConcurrentBlock(initialBlock);
  }

  public void allocateBuffer(ByteBuffer buffer) {
    inputBlock.allocateBuffer(buffer);
  }

  public void signalWrite() {
    inputBlock.signalWrite();
  }
}
