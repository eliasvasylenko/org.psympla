package org.topiello.scanner;

import java.util.stream.Stream;

public interface ScanWindow<C, T> extends AutoCloseable {
  @Override
  void close();

  ScanWindow<C, T> branch();

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

  Stream<C> stream();

  /**
   * Take everything in the interval from the mark position to the given position
   * and reset the mark position to the given position.
   * 
   * @return a text object containing the taken interval
   */
  T takeTo(long position);

  /**
   * Take everything in the interval from the mark position to the given offset
   * from the mark position and reset the mark position to that offset.
   * 
   * @return a text object containing the taken interval
   */
  default T takeToOffset(long offset) {
    return takeTo(retainedPosition() + offset);
  }

  /**
   * Take everything in the interval from the mark position to the input position
   * and reset the mark position to the input position.
   * 
   * @return a text object containing the taken interval
   */
  default T take() {
    return takeTo(scanner().inputPosition());
  }

  void discardTo(long position);

  /**
   * Set the mark position to the current input position, discarding everything
   * prior.
   */
  default void discardToOffset(long offset) {
    discardTo(retainedPosition() + offset);
  }

  /**
   * Set the mark position to the current input position, discarding everything
   * prior.
   */
  default void discard() {
    discardTo(scanner().inputPosition());
  }
}
