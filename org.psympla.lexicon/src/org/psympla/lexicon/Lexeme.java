package org.psympla.lexicon;

public interface Lexeme<C> {
  LexicalClass<C> lexicalClass();

  Sequence<C> characters();

  Token evaluate();
}
