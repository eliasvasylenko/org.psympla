package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Sequence;
import org.psympla.symbol.LexicalItem;

public interface Printer<C, T extends LexicalItem> {
  Sequence<C> print(T parameter);
}
