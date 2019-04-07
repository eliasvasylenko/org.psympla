package org.topiello.lexicon.scanning;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.symbol.Sequence;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class PrintingLexeme<C extends TextUnit, T extends Sequence> implements Lexeme<C, T> {
  private final ScanningLexicalClass<C, T> lexicalClass;
  private final Text<C> characters;
  private final Token<T> token;

  protected PrintingLexeme(
      ScanningLexicalClass<C, T> lexicalClass,
      Text<C> characters,
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
  public Text<C> characters() {
    return characters;
  }

  @Override
  public Token<T> evaluate() {
    return token;
  }
}
