package org.topiello.scanner.bytes.channel;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

import org.topiello.scanner.bytes.Block;
import org.topiello.scanner.bytes.concurrent.ConcurrentBlockContext;
import org.topiello.scanner.bytes.concurrent.ScannerFailedException;

public class ByteChannelBlockContext extends ConcurrentBlockContext {
  private final ByteBufferCache bufferCache;

  private ReadableBytes bytes;
  private ReadableByteChannel byteChannel;

  public ByteChannelBlockContext(ReadableBytes bytes, ByteBufferCache bufferCache) {
    this.bytes = bytes;
    this.bufferCache = bufferCache;
  }

  public synchronized void feed() {
    try {
      synchronized (this) {
        if (bytes == null) {
          return;
        } else {
          this.byteChannel = bytes.openChannel();
          this.bytes = null;
        }
      }

      do {
        var buffer = bufferCache.acquire();
        allocateBuffer(buffer);
        do {
          switch (byteChannel.read(buffer)) {
          case 0:
            throw new NonBlockingChannelException();
          case -1:
            close();
          }
          signalWrite();
        } while (byteChannel.isOpen() && buffer.hasRemaining());
      } while (byteChannel.isOpen());

    } catch (IOException e) {
      throw new ScannerFailedException(e);

    } finally {
      close();
    }
  }

  @Override
  public void release(Block block) {
    bufferCache.release(block.getByteBuffer());
  }

  @Override
  public synchronized void close() {
    try {
      synchronized (this) {
        if (byteChannel != null) {
          byteChannel.close();
        } else {
          bytes = null;
        }
      }
    } catch (IOException e) {
      throw new ScannerFailedException(e);
    }
  }
}
