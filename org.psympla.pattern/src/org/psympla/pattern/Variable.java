package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

/**
 * Variables are identified by name.
 * <p>
 * Multiple instances of {@link Variable} which share the same name may appear
 * within the same scope, but ultimately they must be instantiated to the same
 * value.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 */
public class Variable<T extends LexicalItem> implements Pattern<T> {
  private final String name;

  private Variable(String name) {
    this.name = name;
  }

  public static Variable<?> variable(String name) {
    return new Variable<>(name);
  }

  public static <T extends LexicalItem> Variable<T> variable(
      String name,
      Constraint<T> constraint) {
    return new Variable<>(name);
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + getName() + ")";
  }
}
