package org.psympla.lexicon.scanning;

import org.psympla.symbol.LexicalItem;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public interface Printer<C extends TextUnit, T extends LexicalItem> {
  Text<C> print(T parameter);
}
