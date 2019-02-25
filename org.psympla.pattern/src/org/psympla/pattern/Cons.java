package org.psympla.pattern;

public class Cons implements Pattern {
  private final Pattern car;
  private final Pattern cdr;

  Cons(Pattern car, Pattern cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public Pattern car() {
    return car;
  }

  public Pattern cdr() {
    return cdr;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + car() + ", " + cdr() + ")";
  }
}
