package org.psympla.lexicon;

import java.util.List;

public class Lexeme<C> {
  private final List<C> characters;
  private final Token token;

  public Lexeme(List<C> characters, Token token) {
    this.characters = characters;
    this.token = token;
  }

  public List<C> characters() {
    return characters;
  }

  public Token evaluate() {
    return token;
  }
}
