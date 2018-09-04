package org.psympla.lexicon;

import org.psympla.symbol.Symbol;
import org.psympla.symbol.SymbolicExpression;

public class Token {
  private final Symbol name;
  private final SymbolicExpression value;

  public Token(Symbol name, SymbolicExpression value) {
    this.name = name;
    this.value = value;
  }

  public Symbol name() {
    return name;
  }

  public SymbolicExpression value() {
    return value;
  }
}
