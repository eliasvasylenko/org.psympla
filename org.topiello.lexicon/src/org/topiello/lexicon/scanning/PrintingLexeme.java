package org.topiello.lexicon.scanning;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class PrintingLexeme<T, C extends TextUnit> implements Lexeme<T, C> {
  private final ScanningLexicalClass<T, C> lexicalClass;
  private final Text<C> characters;
  private final Token<T> token;

  protected PrintingLexeme(
      ScanningLexicalClass<T, C> lexicalClass,
      Text<C> characters,
      Token<T> token) {
    this.lexicalClass = lexicalClass;
    this.characters = characters;
    this.token = token;
  }

  @Override
  public LexicalClass<T, C> lexicalClass() {
    return lexicalClass;
  }

  @Override
  public Text<C> characters() {
    return characters;
  }

  @Override
  public Token<T> evaluate() {
    return token;
  }
}
