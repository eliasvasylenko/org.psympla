package org.topiello.scanner.bytes;

public interface BlockFeeder {
  public void allocateBlock(Block inputBlock);

  public int fillBlock(Block block, int limit);

  public Block open();

  /**
   * Indicates that the given input block has been closed by all consumers. Calls
   * to this method are guaranteed to be synchronized and sequential in the order
   * that the blocks were allocated.
   * 
   * @param block
   */
  public void close(Block block);
}
