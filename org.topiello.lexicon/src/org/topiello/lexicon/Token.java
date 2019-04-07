package org.topiello.lexicon;

import org.topiello.symbol.Sequence;
import org.topiello.symbol.Symbol;

public class Token<T extends Sequence> {
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
