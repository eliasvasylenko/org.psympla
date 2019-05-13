package org.topiello.grammar.contextfree;

import org.topiello.grammar.Product;
import org.topiello.grammar.Variable;

public class Symbol implements Product, Variable {
  private final String name;

  public Symbol(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + name + ")";
  }
}
