package org.psympla.semantics;

import org.psympla.symbol.Pattern;
import org.psympla.symbol.SymbolicExpression;
import org.psympla.symbol.Symbol;

public class Signifier<T> {
  private final Symbol name;
  private final Pattern<SymbolicExpression> value;

  public Signifier(Symbol name, Pattern<SymbolicExpression> value) {
    this.name = name;
    this.value = value;}

  public Symbol name() {
    return name;
  }

  public Pattern<SymbolicExpression> valuePattern() {
    return value;
  }
}
