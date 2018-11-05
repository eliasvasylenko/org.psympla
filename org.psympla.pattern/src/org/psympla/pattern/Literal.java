package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

class Literal<T extends LexicalItem> implements Pattern<T> {
  private final T lexicalItem;

  public Literal(T lexicalItem) {
    this.lexicalItem = lexicalItem;
  }

  @Override
  public T instantiate() {
    return lexicalItem;
  }
}
