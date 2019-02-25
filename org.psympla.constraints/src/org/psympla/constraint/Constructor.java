package org.psympla.constraint;

import java.util.Collection;
import java.util.Set;

import org.psympla.pattern.Pattern;
import org.psympla.pattern.Variable;

public class Constructor<T> {
  private final Set<Variable> parameters;
  private final Pattern pattern;

  private Constructor(Collection<? extends Variable> parameters, Pattern pattern) {
    this.parameters = Set.copyOf(parameters);
    this.pattern = pattern;
  }
}
