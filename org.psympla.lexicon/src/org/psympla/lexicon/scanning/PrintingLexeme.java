package org.psympla.lexicon.scanning;

import java.util.List;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;

public class PrintingLexeme<C> implements Lexeme<C> {
  private final ScanningLexicalClass<C> lexicalClass;
  private final List<C> characters;
  private final Token token;

  protected PrintingLexeme(ScanningLexicalClass<C> lexicalClass, List<C> characters, Token token) {
    this.lexicalClass = lexicalClass;
    this.characters = characters;
    this.token = token;
  }

  @Override
  public LexicalClass<C> lexicalClass() {
    return lexicalClass;
  }

  @Override
  public List<C> characters() {
    return characters;
  }

  @Override
  public Token evaluate() {
    return token;
  }
}
