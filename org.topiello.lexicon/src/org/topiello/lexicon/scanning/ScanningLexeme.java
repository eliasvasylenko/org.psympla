package org.topiello.lexicon.scanning;

import org.topiello.lexicon.Token;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Variable;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class ScanningLexeme<T, C extends TextUnit> implements Token<T, C> {
  private final ScanningLexicalClass<T, C> lexicalClass;
  private final Text<C> characters;
  private final Scan<T> scan;
  private Variable<T> token;

  protected ScanningLexeme(
      ScanningLexicalClass<T, C> lexicalClass,
      Text<C> characters,
      Scan<T> scan) {
    this.lexicalClass = lexicalClass;
    this.characters = characters.subSequence(0, scan.length());
    this.scan = scan;
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
    if (token == null) {
      token = new Variable<>(scan.evaluate());
    }
    return token;
  }
}
