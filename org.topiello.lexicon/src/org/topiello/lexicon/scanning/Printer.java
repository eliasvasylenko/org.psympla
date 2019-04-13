package org.topiello.lexicon.scanning;

import java.util.Optional;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface Printer<T, C extends TextUnit> {
  Optional<Text<C>> print(T parameter);
}
