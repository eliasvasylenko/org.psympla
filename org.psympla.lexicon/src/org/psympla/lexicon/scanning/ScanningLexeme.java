package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.Token;
import org.psympla.symbol.LexicalItem;

public class ScanningLexeme<C, T extends LexicalItem> implements Lexeme<C, T> {
  private final ScanningLexicalClass<C, T> lexicalClass;
  private final Sequence<C> characters;
  private final Scan<T> scan;
  private Token<T> token;

  protected ScanningLexeme(
      ScanningLexicalClass<C, T> lexicalClass,
      Sequence<C> characters,
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
  public Sequence<C> characters() {
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
