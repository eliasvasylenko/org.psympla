package org.topiello.scanner.bytes.channel;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.topiello.scanner.bytes.Block;
import org.topiello.scanner.bytes.BlockAllocator;
import org.topiello.scanner.bytes.BlockFeeder;

public class ByteChannelBlockFeeder implements BlockFeeder {
  public static final int DEFAULT_BLOCK_SIZE = 512;

  private final ReadableBytes bytes;
  private final int blockSize;

  public ByteChannelBlockFeeder(ReadableBytes bytes, int blockSize) {
    this.bytes = bytes;
    this.blockSize = blockSize;
  }

  public ByteChannelBlockFeeder(ReadableBytes bytes) {
    this(bytes, DEFAULT_BLOCK_SIZE);
  }

  @Override
  public void feed(BlockAllocator allocator, Block firstBlock) throws IOException {
    var byteChannel = bytes.openChannel();
    var block = firstBlock;
    do {
      var buffer = ByteBuffer.allocate(blockSize);
      var nextBlock = block.allocateBuffer(buffer);
      do {
        if (byteChannel.read(buffer) < 0) {
          return;
        }
      } while (buffer.hasRemaining());
      block = nextBlock;
    } while (block != null);
  }
}
