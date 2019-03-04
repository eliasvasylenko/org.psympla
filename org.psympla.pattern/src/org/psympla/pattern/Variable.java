package org.psympla.pattern;

import java.util.Objects;

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
public class Variable implements Pattern {
  private final String name;

  Variable(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Variable other = (Variable) obj;
    return Objects.equals(name, other.name);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + name() + ")";
  }
}
