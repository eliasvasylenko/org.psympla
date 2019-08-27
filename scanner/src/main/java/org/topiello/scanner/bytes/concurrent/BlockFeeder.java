package org.topiello.scanner.bytes.concurrent;

import java.io.IOException;

public interface BlockFeeder {
  void feed(ConcurrentBlock firstBlock) throws IOException;
}
