package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.Token;

public class ScanningLexeme<C> implements Lexeme<C> {
  private final ScanningLexicalClass<C> lexicalClass;
  private final Sequence<C> characters;
  private final Scan scan;
  private Token token;

  protected ScanningLexeme(
      ScanningLexicalClass<C> lexicalClass,
      Sequence<C> characters,
      Scan scan) {
    this.lexicalClass = lexicalClass;
    this.characters = characters.subSequence(0, scan.length());
    this.scan = scan;
  }

  @Override
  public LexicalClass<C> lexicalClass() {
    return lexicalClass;
  }

  @Override
  public Sequence<C> characters() {
    return characters;
  }

  @Override
  public Token evaluate() {
    if (token == null) {
      token = new Token(lexicalClass.symbol(), scan.evaluate());
    }
    return token;
  }
}
