package org.topiello.semantics;

import org.topiello.pattern.Patterns;
import org.topiello.pattern.Variable;
import org.topiello.semantics.type.SymbolType;
import org.topiello.symbol.LexicalItem;

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
