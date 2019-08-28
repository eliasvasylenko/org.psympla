package org.topiello.scanner.bytes.concurrent;

import java.io.IOException;

import org.topiello.scanner.bytes.BlockScanner;

public interface BlockFeeder {
  void feed(ConcurrentBlock firstBlock) throws IOException;

  default BlockScanner openScanner() {
    return new BlockScanner(new ConcurrentBlockContext(this));
  }
}
