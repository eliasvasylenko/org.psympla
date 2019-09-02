package org.topiello.scanner;

import java.util.function.Predicate;

/**
 * A scanner buffers a moving window between the {@link #retainedPosition()
 * retained position} and the {@link #inputPosition() input position}.
 * 
 * @author eli
 *
 * @param <C>
 * @param <T>
 */
public interface Scanner<C, T> {
  void close();

  /**
   * @return the input position in the buffered text
   */
  long inputPosition();

  Cursor<C> peek();

  Cursor<C> advance();

  /**
   * Increase the input position until the character at that position matchs the
   * given predicate
   * 
   * @param condition
   * @return the cursor at the first input position which doesn't match the
   *         predicate, which may be the end of input
   */
  Cursor<C> advanceWhile(Predicate<C> condition);

  Cursor<C> advanceTo(long inputPosition);

  Scanner<C, T> branch();

  ScanWindow<C, T> openWindow();
}
