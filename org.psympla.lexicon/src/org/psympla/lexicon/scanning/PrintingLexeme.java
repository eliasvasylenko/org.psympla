package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Characters;
import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.symbol.Sequence;

public class PrintingLexeme<C, T extends Sequence> implements Lexeme<C, T> {
  private final ScanningLexicalClass<C, T> lexicalClass;
  private final Characters<C> characters;
  private final Token<T> token;

  protected PrintingLexeme(
      ScanningLexicalClass<C, T> lexicalClass,
      Characters<C> characters,
      Token<T> token) {
    this.lexicalClass = lexicalClass;
    this.characters = characters;
    this.token = token;
  }

  @Override
  public LexicalClass<C, T> lexicalClass() {
    return lexicalClass;
  }

  @Override
  public Characters<C> characters() {
    return characters;
  }

  @Override
  public Token<T> evaluate() {
    return token;
  }
}
