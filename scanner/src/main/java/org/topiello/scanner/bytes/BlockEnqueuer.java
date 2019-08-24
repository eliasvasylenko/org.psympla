package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

public interface BlockEnqueuer {
  ByteBlock enqueue(ByteBuffer byteBuffer, Consumer<ByteBlock> onClose);
}
