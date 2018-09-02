package org.psympla.lexicon;

import org.psympla.grammar.Sequence;

public interface Scan<C> {
  int length();

  Sequence parameters();
}
