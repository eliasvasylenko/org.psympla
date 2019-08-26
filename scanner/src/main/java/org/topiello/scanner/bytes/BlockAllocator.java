package org.topiello.scanner.bytes;

public interface BlockAllocator {
  /**
   * @param block
   *          if the given block does not belong to this receiver, the behaviour
   *          is undefined
   */
  public void awaitAllocation(Block block);

  /**
   * @param block
   *          if the given block does not belong to this receiver, the behaviour
   *          is undefined
   */
  public void awaitData(Block block, int limit);

  public Block open();

  /**
   * Indicates that the given input block has been closed by all consumers. Calls
   * to this method are guaranteed to be synchronized and sequential in the order
   * that the blocks were allocated.
   * 
   * @param block
   */
  public void release(Block block);

  public void close();
}
