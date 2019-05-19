package org.topiello.pattern;

import org.topiello.pattern.symbol.LexicalItem;

public class Literal implements Pattern {
  private final LexicalItem lexicalItem;

  Literal(LexicalItem lexicalItem) {
    this.lexicalItem = lexicalItem;
  }

  public LexicalItem lexicalItem() {
    return lexicalItem;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + lexicalItem() + ")";
  }
}
