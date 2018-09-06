package org.psympla.lexicon;

import org.psympla.symbol.Symbol;
import org.psympla.symbol.Cell;

public class Token {
  private final Symbol name;
  private final Cell value;

  public Token(Symbol name, Cell value) {
    this.name = name;
    this.value = value;
  }

  public Symbol name() {
    return name;
  }

  public Cell value() {
    return value;
  }
}
