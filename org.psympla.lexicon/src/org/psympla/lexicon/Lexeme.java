package org.psympla.lexicon;

import org.psympla.symbol.Sequence;

public interface Lexeme<C, T extends Sequence> {
  LexicalClass<C, T> lexicalClass();

  Characters<C> characters();

  Token<T> evaluate();
}
