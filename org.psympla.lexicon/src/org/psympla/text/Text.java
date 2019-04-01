package org.psympla.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Text<C extends TextUnit> extends Iterable<C> {
  Text<C> subSequence(int from, int to);

  default Text<C> subSequence(int to) {
    return subSequence(0, to);
  }

  static <C extends TextUnit> Text<C> empty() {
    return new Text<C>() {
      @Override
      public Text<C> subSequence(int from, int to) {
        if (from != 0 || to != 0)
          throw new IndexOutOfBoundsException();
        return this;
      }

      @Override
      public Iterator<C> iterator() {
        return new Iterator<C>() {
          @Override
          public boolean hasNext() {
            return false;
          }

          @Override
          public C next() {
            throw new NoSuchElementException();
          }
        };
      }
    };
  }

  default boolean startsWith(Text<C> that) {
    var thisIterator = this.iterator();
    var thatIterator = that.iterator();

    while (thatIterator.hasNext()) {
      if (!thisIterator.hasNext()) {
        return false;
      }

      if (!Objects.equals(thisIterator.next(), thatIterator.next())) {
        return false;
      }
    }

    return true;
  }
}
