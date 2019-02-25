package org.psympla.lexicon;

import org.psympla.symbol.LexicalItem;

public interface Lexeme<C, T extends LexicalItem> {
  LexicalClass<C, T> lexicalClass();

  Sequence<C> characters();

  Token<T> evaluate();
}
