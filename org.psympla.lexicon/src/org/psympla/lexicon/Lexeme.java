package org.psympla.lexicon;

import java.util.List;

public interface Lexeme<C> {
  LexicalClass<C> lexicalClass();

  List<C> characters();

  Token evaluate();
}
