package org.topiello.scanner.bytes;

public interface BlockFeeder {
  void feed(BlockAllocator allocator, Block firstBlock);
}
