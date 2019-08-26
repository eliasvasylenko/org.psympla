package org.topiello.scanner.bytes.concurrent;

import java.nio.ByteBuffer;

import org.topiello.scanner.bytes.Block;
import org.topiello.scanner.bytes.BlockAllocator;
import org.topiello.scanner.bytes.ScannerInterruptedException;

public abstract class ConcurrentBlockAllocator implements BlockAllocator {
  private volatile ConcurrentBlock inputBlock;

  public ConcurrentBlockAllocator() {
    this.inputBlock = new ConcurrentBlock(new Block(this));
  }

  @Override
  public void awaitAllocation(Block block) {
    inputBlock.awaitAllocation(block);
  }

  @Override
  public int awaitData(Block block, int limit) {
    try {
      bufferAllocationLatch.await();
    } catch (InterruptedException e) {
      throw new ScannerInterruptedException(e);
    }
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Block open() {
    return inputBlock.block();
  }
}
