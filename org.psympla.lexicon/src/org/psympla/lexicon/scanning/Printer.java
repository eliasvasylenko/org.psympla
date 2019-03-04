package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Characters;
import org.psympla.symbol.LexicalItem;

public interface Printer<C, T extends LexicalItem> {
  Characters<C> print(T parameter);
}
