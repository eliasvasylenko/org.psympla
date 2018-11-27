package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

public class LiteralShape extends Shape {
  private final LexicalItem lexicalItem;

  LiteralShape(LexicalItem lexicalItem) {
    this.lexicalItem = lexicalItem;
  }

  public LexicalItem getLexicalItem() {
    return lexicalItem;
  }

  @Override
  public String toString() {
    return lexicalItem.toString();
  }
}
