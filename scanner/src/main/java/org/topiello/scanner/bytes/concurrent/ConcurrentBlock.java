package org.topiello.scanner.bytes.concurrent;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.topiello.scanner.ScannerFailedException;
import org.topiello.scanner.ScannerInterruptedException;
import org.topiello.scanner.bytes.Block;

// TODO value / inline type with valhalla
public final class ConcurrentBlock {
  private final Block block;

  private final CompletableFuture<ConcurrentBlock> next = new CompletableFuture<>();
  private final ReentrantLock writeLock = new ReentrantLock();
  private final Condition writeDone = writeLock.newCondition();

  public ConcurrentBlock(Block block) {
    this.block = Objects.requireNonNull(block);
  }

  public Block block() {
    return block;
  }

  public void awaitAllocation(Block block) {
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

  ConcurrentBlock allocateBuffer(ByteBuffer buffer) {
    var next = new ConcurrentBlock(block.allocateBuffer(buffer));
    this.next.complete(next);
    return next;
  }

  public void awaitData(Block block, int limit) {
    if (block == this.block) {
      awaitData(limit);
    } else if (block.startPosition() > this.block.startPosition()) {
      next.join().awaitData(block, limit);
    }
  }

  private void awaitData(int limit) {
    writeLock.lock();
    try {
      while (block.getByteBuffer().position() < limit) {
        writeDone.await();
      }
    } catch (InterruptedException e) {
      throw new ScannerInterruptedException(e);
    } finally {
      writeLock.unlock();
    }
  }

  void signalWrite() {
    writeLock.lock();
    try {
      writeDone.signalAll();
    } finally {
      writeLock.unlock();
    }
  }
}
