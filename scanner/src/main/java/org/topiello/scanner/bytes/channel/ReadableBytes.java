package org.topiello.scanner.bytes.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Executor;

import org.topiello.scanner.Scannable;
import org.topiello.scanner.Scanner;

public interface ReadableBytes extends Scannable<Byte, ByteBuffer> {
  ReadableByteChannel openChannel() throws IOException;

  ByteBufferCache byteBufferCache();

  @Override
  default Scanner<Byte, ByteBuffer> openScanner(Executor executor) {
    var scanner = new ByteChannelScanner(this, byteBufferCache());
    executor.execute(scanner::feed);
    return scanner;
  }
}
