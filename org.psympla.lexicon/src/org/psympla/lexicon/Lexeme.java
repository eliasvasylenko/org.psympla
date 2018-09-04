package org.psympla.lexicon;

import java.util.List;

public interface Lexeme<C> {
  List<C> characters();

  Token evaluate();
}
