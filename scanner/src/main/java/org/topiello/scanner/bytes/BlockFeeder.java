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

  public InputBlock open();

  public void close(InputBlock block);
}
