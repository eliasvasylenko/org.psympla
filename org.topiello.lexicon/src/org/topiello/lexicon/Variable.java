package org.topiello.lexicon;

public class Variable<T> {
  private final T variable;

  public Variable(T variable) {
    this.variable = variable;
  }

  public T variable() {
    return variable;
  }
}
