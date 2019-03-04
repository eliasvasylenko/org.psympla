package org.psympla.lexicon;

public interface Characters<C> {
  Characters<C> subSequence(int from, int to);

  default Characters<C> subSequence(int to) {
    return subSequence(0, to);
  }
}
