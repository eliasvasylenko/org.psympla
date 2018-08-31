package org.psympla.grammar;

public class Expression implements LexicalItem<Expression> {
  private final Symbol symbol;
  private final Sequence parameters;

  public Expression(Symbol name, Sequence sequence) {
    this.symbol = name;
    this.parameters = sequence;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public Sequence getParameters() {
    return parameters;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + symbol + " " + parameters + ")";
  }
}
