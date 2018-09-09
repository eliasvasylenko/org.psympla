package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.Token;

public class PrintingLexeme<C> implements Lexeme<C> {
  private final ScanningLexicalClass<C> lexicalClass;
  private final Sequence<C> characters;
  private final Token token;

  protected PrintingLexeme(
      ScanningLexicalClass<C> lexicalClass,
      Sequence<C> characters,
      Token token) {
    this.lexicalClass = lexicalClass;
    this.characters = characters;
    this.token = token;
  }

  @Override
  public LexicalClass<C> lexicalClass() {
    return lexicalClass;
  }

  @Override
  public Sequence<C> characters() {
    return characters;
  }

  @Override
  public Token evaluate() {
    return token;
  }
}
