package org.topiello.scanner;

import java.util.stream.Stream;

public interface ScanWindow<C, T> extends AutoCloseable {
  @Override
  void close();

  Scanner<C, T> scanner();

  /**
   * @return the retained position in the buffered text
   */
  long retainedPosition();

  /**
   * @return the input position relative to the current retained position.
   */
  default long windowSize() {
    return scanner().inputPosition() - retainedPosition();
  }

  Stream<C> streamPositionInterval(long fromPosition, long toPosition);

  default Stream<C> streamOffsetInterval(long fromOffset, long toOffset) {
    return streamPositionInterval(retainedPosition() + fromOffset, retainedPosition() + toOffset);
  }

  default Stream<C> stream() {
    return streamPositionInterval(retainedPosition(), scanner().inputPosition());
  }

  /**
   * Take everything in the interval from the mark position to the given position
   * and reset the mark position to the given position.
   * 
   * @return a text object containing the taken interval
   */
  T takeToPosition(long position);

  /**
   * Take everything in the interval from the mark position to the given offset
   * from the mark position and reset the mark position to that offset.
   * 
   * @return a text object containing the taken interval
   */
  default T takeToOffset(long offset) {
    return takeToPosition(retainedPosition() + offset);
  }

  /**
   * Take everything in the interval from the mark position to the input position
   * and reset the mark position to the input position.
   * 
   * @return a text object containing the taken interval
   */
  default T take() {
    return takeToPosition(scanner().inputPosition());
  }

  void discardToPosition(long position);

  /**
   * Set the mark position to the current input position, discarding everything
   * prior.
   */
  default void discardToOffset(long offset) {
    discardToPosition(retainedPosition() + offset);
  }

  /**
   * Set the mark position to the current input position, discarding everything
   * prior.
   */
  default void discard() {
    discardToPosition(scanner().inputPosition());
  }
}
