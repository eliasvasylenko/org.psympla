package org.topiello.scanner.bytes;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

public interface BlockEnqueuer {
  InputBlock enqueue(ByteBuffer byteBuffer, Consumer<InputBlock> onClose);
}
