package org.topiello.scanner.bytes.channel;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public interface ReadableBytes {
  ReadableByteChannel openChannel() throws IOException;
}
