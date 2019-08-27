package org.topiello.scanner.bytes.concurrent;

import java.nio.ByteBuffer;

import org.topiello.scanner.bytes.Block;
import org.topiello.scanner.bytes.BlockContext;
import org.topiello.scanner.bytes.ScannerInterruptedException;

public abstract class ConcurrentBlockAllocator implements BlockContext {
  private volatile ConcurrentBlock inputBlock;

  public ConcurrentBlockAllocator() {
    this.inputBlock = new ConcurrentBlock(new Block(this));
  }

  @Override
  public void awaitAllocation(Block block) {
    inputBlock.awaitAllocation(block);
  }

  @Override
  public void awaitData(Block block, int limit) {
    try {
      bufferAllocationLatch.await();
    } catch (InterruptedException e) {
      throw new ScannerInterruptedException(e);
    }
    // TODO Auto-generated method stub
  }

  @Override
  public Block open() {
    return inputBlock.block();
  }
}
