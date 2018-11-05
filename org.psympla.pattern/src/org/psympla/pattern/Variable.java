package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

public class Variable<T extends LexicalItem> implements Pattern<T> {
  private final String name;

  private Variable(String name) {
    this.name = name;
  }

  public static Variable<?> named(String name) {
    return new Variable<>(name);
  }

  public <U extends LexicalItem> Variable<U> typed(Class<U> type) {
    // TODO Auto-generated method stub
    return null;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + getName() + ")";
  }

  @Override
  public T instantiate() {
    throw new InstantiationMissingException(this);
  }
}
