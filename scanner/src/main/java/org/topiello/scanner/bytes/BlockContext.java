package org.topiello.scanner.bytes;

public interface BlockContext {
  public void open(Block initialBlock);

  /**
   * @param block
   *          if the given block does not belong to this receiver, the behavior is
   *          undefined
   */
  public void awaitAllocation(Block block);

  /**
   * @param block
   *          if the given block does not belong to this receiver, the behavior is
   *          undefined
   */
  public void awaitData(Block block, int limit);

  /**
   * Indicates that the given input block has been closed by all consumers. Calls
   * to this method are guaranteed to be synchronized and sequential in the order
   * that the blocks were allocated.
   * 
   * @param block
   */
  public void release(Block block);

  /**
   * Calls to this method are guaranteed to occur after all other invocations of
   * methods on this class have completed.
   */
  public void close();
}
