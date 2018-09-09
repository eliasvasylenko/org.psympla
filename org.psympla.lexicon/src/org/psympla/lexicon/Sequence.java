package org.psympla.lexicon;

public interface Sequence<C> {
  Sequence<C> subSequence(int from, int to);
}
