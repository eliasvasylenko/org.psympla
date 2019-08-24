package org.topiello.scanner.bytes;

public interface BlockFeeder {
  public void allocateBlock(ByteBlock inputBlock);

  public int fillBlock(ByteBlock block, int limit);

  public ByteBlock open();

  /**
   * Indicates that the given input block has been closed by all consumers. Calls
   * to this method are guaranteed to be synchronized and sequential in the order
   * that the blocks were allocated.
   * 
   * @param block
   */
  public void close(ByteBlock block);
}
