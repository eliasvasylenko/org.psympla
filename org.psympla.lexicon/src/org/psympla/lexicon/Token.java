package org.psympla.lexicon;

import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;

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
