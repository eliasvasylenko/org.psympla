package org.psympla.pattern;

import java.util.List;

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
  private final List<Constraint> constraints;

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
}
