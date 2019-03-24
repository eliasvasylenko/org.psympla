package org.psympla.lexicon;

/**
 * 
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Characters<C> {
  Characters<C> subSequence(int from, int to);

  default Characters<C> subSequence(int to) {
    return subSequence(0, to);
  }

  static <C> Characters<C> empty() {
    return new Characters<C>() {
      @Override
      public Characters<C> subSequence(int from, int to) {
        if (from != 0 || to != 0)
          throw new IndexOutOfBoundsException();
        return this;
      }
    };
  }
}
