package org.topiello.lexicon.scanning;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.symbol.Sequence;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class ScanningLexeme<C extends TextUnit, T extends Sequence> implements Lexeme<C, T> {
  private final ScanningLexicalClass<C, T> lexicalClass;
  private final Text<C> characters;
  private final Scan<T> scan;
  private Token<T> token;

  protected ScanningLexeme(
      ScanningLexicalClass<C, T> lexicalClass,
      Text<C> characters,
      Scan<T> scan) {
    this.lexicalClass = lexicalClass;
    this.characters = characters.subSequence(0, scan.length());
    this.scan = scan;
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
    if (token == null) {
      token = new Token<>(lexicalClass.symbol(), scan.evaluate());
    }
    return token;
  }
}
