package org.psympla.lexicon.scanning;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.symbol.Sequence;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

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
