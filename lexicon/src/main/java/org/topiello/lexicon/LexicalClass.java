package org.topiello.lexicon;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface LexicalClass<T extends Token<?, ?>, C extends TextUnit> {
  Stream<Lexeme<T, C>> scan(Text<C> characters);

  Optional<Lexeme<T, C>> print(T token);
}
