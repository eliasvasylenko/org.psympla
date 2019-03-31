package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.symbol.Sequence;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

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
