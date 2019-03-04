package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Characters;
import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.symbol.Sequence;

public class ScanningLexeme<C, T extends Sequence> implements Lexeme<C, T> {
  private final ScanningLexicalClass<C, T> lexicalClass;
  private final Characters<C> characters;
  private final Scan<T> scan;
  private Token<T> token;

  protected ScanningLexeme(
      ScanningLexicalClass<C, T> lexicalClass,
      Characters<C> characters,
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
  public Characters<C> characters() {
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
