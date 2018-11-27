package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

public class Pattern<T extends LexicalItem> {
  private final Shape shape;
  private final Constraints constraints;

  public Pattern(Shape shape, Constraints constraints) {
    this.shape = shape;
    this.constraints = constraints;
  }

  public Shape shape() {
    return shape;
  }

  public Constraints constraints() {
    return constraints;
  }
}
