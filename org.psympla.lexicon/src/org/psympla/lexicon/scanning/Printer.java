package org.psympla.lexicon.scanning;

import java.util.Optional;

import org.psympla.symbol.LexicalItem;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public interface Printer<C extends TextUnit, T extends LexicalItem> {
  Optional<Text<C>> print(T parameter);
}
