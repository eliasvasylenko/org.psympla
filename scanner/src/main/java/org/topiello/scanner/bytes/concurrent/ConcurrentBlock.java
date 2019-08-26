package org.topiello.scanner.bytes.concurrent;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.topiello.scanner.bytes.Block;
import org.topiello.scanner.bytes.ScannerInterruptedException;

// TODO value / inline type with valhalla
public final class ConcurrentBlock {
  private final Block block;
  private final CompletableFuture<ConcurrentBlock> next;

  public ConcurrentBlock(Block block) {
    this.block = Objects.requireNonNull(block);
    this.next = new CompletableFuture<>();
  }

  public Block block() {
    return block;
  }

  void awaitAllocation(Block block) {
    if (block == this.block) {
      awaitAllocation();
    } else if (block.startPosition() > this.block.startPosition()) {
      next.join().awaitAllocation(block);
    }
  }

  private void awaitAllocation() {
    try {
      next.get();
    } catch (InterruptedException e) {
      throw new ScannerInterruptedException(e);
    } catch (ExecutionException e) {
      throw new ScannerFailedException(e);
    }
  }

  public ConcurrentBlock allocateBuffer(ByteBuffer buffer) {
    var next = new ConcurrentBlock(block.allocateBuffer(buffer));
    this.next.complete(next);
    return next;
  }

  public boolean signalWrite() {
    // TODO Auto-generated method stub

  }
}
