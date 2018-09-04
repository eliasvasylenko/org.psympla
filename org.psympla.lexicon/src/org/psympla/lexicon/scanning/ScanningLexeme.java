package org.psympla.lexicon.scanning;

import java.util.List;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.Token;

public class ScanningLexeme<C> implements Lexeme<C> {
  private final ScanningLexicalClass<C> lexicalClass;
  private final List<C> characters;
  private final Scan scan;
  private Token token;

  protected ScanningLexeme(ScanningLexicalClass<C> lexicalClass, List<C> characters, Scan scan) {
    this.lexicalClass = lexicalClass;
    this.characters = characters.subList(0, scan.length());
    this.scan = scan;
  }

  @Override
  public List<C> characters() {
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
