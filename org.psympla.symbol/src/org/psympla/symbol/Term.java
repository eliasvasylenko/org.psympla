package org.psympla.symbol;

public class Term extends Cell {
  protected Term(Symbol car, LexicalItem cdr) {
    super(car, cdr);
  }

  @Override
  public Symbol car() {
    return (Symbol) super.car();
  }
}
