package org.topiello.lexicon.scanning;

import java.util.Optional;

import org.topiello.symbol.LexicalItem;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface Printer<C extends TextUnit, T extends LexicalItem> {
  Optional<Text<C>> print(T parameter);
}
