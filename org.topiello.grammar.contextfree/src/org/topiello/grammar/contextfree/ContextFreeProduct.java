package org.topiello.grammar.contextfree;

import org.topiello.grammar.Product;

// TODO value type
public class ContextFreeProduct implements Product<Symbol> {
  private final Symbol symbol;

  public ContextFreeProduct(Symbol symbol) {
    this.symbol = symbol;
  }

  public Symbol symbol() {
    return symbol;
  }
}
