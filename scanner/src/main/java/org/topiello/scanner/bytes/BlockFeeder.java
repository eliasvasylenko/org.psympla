package org.topiello.scanner.bytes;

public interface BlockFeeder {
  /**
   * Attempt to buffer input beyond the given position.
   * <p>
   * If input ends at or before the given position, the remainder of the input
   * should be buffered, then the last input position returned. Otherwise, input
   * should be buffered to some point beyond the given position, and the last
   * buffered input position returned.
   * 
   * @param inputPosition
   * @return
   */
  public long feedTo(long inputPosition);

  public long feedToBlock(int index);

  public InputBlock open();

  /**
   * Indicates that the given input block has been closed by all consumers. Calls
   * to this method are guaranteed to be synchronized and sequential in the order
   * that the blocks were allocated.
   * 
   * @param block
   */
  public void close(InputBlock block);
}
