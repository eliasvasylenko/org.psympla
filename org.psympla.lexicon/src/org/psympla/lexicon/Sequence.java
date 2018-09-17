package org.psympla.lexicon;

import org.psympla.symbol.LexicalItem;

public interface Sequence<C> extends LexicalItem<Sequence<C>> {
  Sequence<C> subSequence(int from, int to);

  default Sequence<C> subSequence(int to) {
    return subSequence(0, to);
  }
}
