package org.psympla.lexicon;

import java.util.stream.Stream;

import org.psympla.symbol.Symbol;

public interface LexicalClass<C> {
  Symbol symbol();

  Stream<Lexeme<C>> scan(Sequence<C> characters);

  Lexeme<C> print(Token token);
}
