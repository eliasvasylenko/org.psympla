package org.topiello.pattern.constraint;

import java.util.Collection;
import java.util.Set;

import org.topiello.pattern.Pattern;
import org.topiello.pattern.Variable;

public class Constructor<T> {
  private final Set<Variable> parameters;
  private final Pattern pattern;

  private Constructor(Collection<? extends Variable> parameters, Pattern pattern) {
    this.parameters = Set.copyOf(parameters);
    this.pattern = pattern;
  }
}
