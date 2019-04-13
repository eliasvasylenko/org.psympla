package org.topiello.lexicon;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

/*
 * The c
 */
public interface LexicalClass<T, C extends TextUnit> {
  T variable();

  Stream<Lexeme<T, C>> scan(Text<C> characters);

  Optional<Lexeme<T, C>> print(Token<T> token);
}
