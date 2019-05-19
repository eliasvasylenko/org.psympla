package org.topiello.lexicon;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface LexicalClass<T, C extends TextUnit> {
  Stream<Token<T, C>> scan(Text<C> characters);

  Optional<Token<T, C>> print(Variable<T> token);
}
