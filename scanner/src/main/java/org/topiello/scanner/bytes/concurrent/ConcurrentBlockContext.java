package org.topiello.scanner.bytes.concurrent;

import java.nio.ByteBuffer;

import org.topiello.scanner.bytes.Block;
import org.topiello.scanner.bytes.BlockContext;
import org.topiello.scanner.bytes.ScannerInterruptedException;

public class ConcurrentBlockContext implements BlockContext {
  private volatile ConcurrentBlock inputBlock;

  public ConcurrentBlockContext(BlockFeeder blockFeeder) {
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

  @Override
  public void open(Block initialBlock) {
    // TODO Auto-generated method stub

  }

  @Override
  public void release(Block block) {
    // TODO Auto-generated method stub

  }

  @Override
  public void close() {
    // TODO Auto-generated method stub

  }
}
