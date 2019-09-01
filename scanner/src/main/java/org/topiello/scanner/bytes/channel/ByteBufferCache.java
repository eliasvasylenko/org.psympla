package org.topiello.scanner.bytes.channel;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ByteBufferCache {
  public static final int DEFAULT_BUFFER_SIZE = 512;
  public static final int DEFAULT_CAPACITY = 10;

  /*
   * TODO shared instances should probably use soft references, and a reference
   * queue to clear the broken references every new release
   */
  private final Queue<ByteBuffer> cache;
  private final int bufferSize;
  private final int capacity;

  protected ByteBufferCache(int bufferSize, int capacity, Queue<ByteBuffer> queue) {
    this.bufferSize = bufferSize;
    this.capacity = capacity;
    this.cache = queue;
  }

  public static ByteBufferCache createPrivate() {
    return createPrivate(DEFAULT_BUFFER_SIZE, DEFAULT_CAPACITY);
  }

  public static ByteBufferCache createPrivate(int bufferSize, int capacity) {
    return new ByteBufferCache(bufferSize, capacity, new LinkedList<>());
  }

  public static ByteBufferCache createShared() {
    return createShared(DEFAULT_BUFFER_SIZE, DEFAULT_CAPACITY);
  }

  public static ByteBufferCache createShared(int bufferSize, int capacity) {
    return new ByteBufferCache(bufferSize, capacity, new ConcurrentLinkedQueue<>());
  }

  ByteBuffer acquire() {
    var buffer = cache.poll();
    if (buffer == null) {
      buffer = ByteBuffer.allocate(bufferSize);
    }
    return buffer;
  }

  void release(ByteBuffer buffer) {
    // in lieu of a thread safe capacity check
    if (cache.size() < capacity) {
      cache.offer(buffer);
      if (cache.size() > capacity) {
        cache.poll();
      }
    }
  }
}
