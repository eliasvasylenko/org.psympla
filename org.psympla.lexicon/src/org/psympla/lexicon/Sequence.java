package org.psympla.lexicon;

public interface Sequence<C> {
  Sequence<C> subSequence(int from, int to);

  default Sequence<C> subSequence(int to) {
    return subSequence(0, to);
  }
}
