package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.Token;
import org.psympla.symbol.LexicalItem;

public class PrintingLexeme<C, T extends LexicalItem> implements Lexeme<C, T> {
  private final ScanningLexicalClass<C, T> lexicalClass;
  private final Sequence<C> characters;
  private final Token<T> token;

  protected PrintingLexeme(
      ScanningLexicalClass<C, T> lexicalClass,
      Sequence<C> characters,
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
  public Sequence<C> characters() {
    return characters;
  }

  @Override
  public Token<T> evaluate() {
    return token;
  }
}
