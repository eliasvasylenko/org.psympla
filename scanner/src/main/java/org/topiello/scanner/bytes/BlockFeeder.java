package org.topiello.scanner.bytes;

import java.io.IOException;

public interface BlockFeeder {
  void feed(BlockAllocator allocator, Block firstBlock) throws IOException;
}
