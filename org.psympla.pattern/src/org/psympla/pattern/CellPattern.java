package org.psympla.pattern;

import org.psympla.symbol.Cell;

public class CellPattern implements Pattern<Cell> {
  private final Pattern<?> car;
  private final Pattern<?> cdr;

  protected CellPattern(Pattern<?> car, Pattern<?> cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public Pattern<?> car() {
    return car;
  }

  public Pattern<?> cdr() {
    return cdr;
  }

  @Override
  public Cell instantiate() {
    return null;
  }
}
