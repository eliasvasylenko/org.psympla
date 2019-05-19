package org.topiello.lexicon.scanning;

import org.topiello.lexicon.Token;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Variable;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class PrintingLexeme<T, C extends TextUnit> implements Token<T, C> {
  private final ScanningLexicalClass<T, C> lexicalClass;
  private final Text<C> characters;
  private final Variable<T> token;

  protected PrintingLexeme(
      ScanningLexicalClass<T, C> lexicalClass,
      Text<C> characters,
      Variable<T> token) {
    this.lexicalClass = lexicalClass;
    this.characters = characters;
    this.token = token;
  }

  @Override
  public LexicalClass<T, C> lexicalClass() {
    return lexicalClass;
  }

  @Override
  public Text<C> lexeme() {
    return characters;
  }

  @Override
  public Variable<T> evaluate() {
    return token;
  }
}
