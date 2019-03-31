package org.psympla.text;

/**
 * 
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Text<C extends TextUnit> {
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
    };
  }

  default boolean startsWith(Text<C> textValue) {
    throw new UnsupportedOperationException();
  }
}
