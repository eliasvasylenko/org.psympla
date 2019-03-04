package org.psympla.semantics;

import org.psympla.pattern.Patterns;
import org.psympla.pattern.Variable;
import org.psympla.semantics.type.SymbolType;
import org.psympla.symbol.LexicalItem;

public class Unknown<T extends LexicalItem> {
  private final Variable variable;
  private final SymbolType<T> type;

  public Unknown(Variable variable, SymbolType<T> type) {
    this.variable = variable;
    this.type = type;
  }

  public Unknown(String variableName, SymbolType<T> type) {
    this.variable = Patterns.variable(variableName);
    this.type = type;
  }

  public Variable variable() {
    return variable;
  }

  public SymbolType<T> type() {
    return type;
  }
}
