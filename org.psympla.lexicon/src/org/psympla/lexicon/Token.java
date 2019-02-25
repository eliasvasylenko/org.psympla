package org.psympla.lexicon;

import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;

public class Token<T extends LexicalItem> {
  private final Symbol name;
  private final T value;

  public Token(Symbol name, T value) {
    this.name = name;
    this.value = value;
  }

  public Symbol name() {
    return name;
  }

  public T value() {
    return value;
  }
}
