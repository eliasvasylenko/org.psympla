package org.topiello.lexicon;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

/*
 * The c
 */
public interface LexicalClass<C extends TextUnit> {
  Stream<Lexeme<?, C>> scan(Text<C> characters);

  Optional<Lexeme<?, C>> print(Token<?> token);
}
