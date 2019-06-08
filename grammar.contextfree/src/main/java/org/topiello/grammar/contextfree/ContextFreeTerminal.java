package org.topiello.grammar.contextfree;

import org.topiello.grammar.Terminal;
import org.topiello.text.TextUnit;

// TODO value type
public class ContextFreeTerminal<C extends TextUnit> implements Terminal<C> {
  private final Symbol variable;

  public ContextFreeTerminal(Symbol variable) {
    this.variable = variable;
  }

  @Override
  public Symbol variable() {
    return variable;
  }

  @Override
  public String toString() {
    return variable.toString();
  }
}
