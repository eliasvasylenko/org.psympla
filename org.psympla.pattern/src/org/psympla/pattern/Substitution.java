package org.psympla.pattern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.psympla.symbol.LexicalItem;

public class Substitution {
  private static final Substitution EMPTY = new Substitution();
  private final Map<Variable<?>, ? extends Pattern<?>> instantiations;

  private Substitution() {
    instantiations = Collections.emptyMap();
  }

  private Substitution(Map<Variable<?>, ? extends Pattern<?>> instantiations) {
    this.instantiations = instantiations;
  }

  public static Substitution empty() {
    return EMPTY;
  }

  public Stream<Variable<?>> variables() {
    return instantiations.keySet().stream();
  }

  @SuppressWarnings("unchecked")
  public <T extends LexicalItem> Pattern<T> instantiate(Variable<T> variable) {
    var instantiation = instantiations.get(variable);
    if (instantiation == null) {
      return variable;
    }
    return (Pattern<T>) instantiation;
  }

  public <T extends LexicalItem> Substitution with(Variable<T> variable, Pattern<T> pattern) {
    Map<Variable<?>, Pattern<?>> instantiations = new HashMap<>(this.instantiations);
    instantiations.put(variable, pattern);
    return new Substitution(instantiations);
  }

  @SuppressWarnings("unchecked")
  public <T extends LexicalItem> Pattern<T> substitute(Pattern<T> pattern) {
    if (pattern instanceof Variable<?>) {
      return instantiate((Variable<T>) pattern);

    } else if (pattern instanceof Literal<?>) {
      return pattern;

    } else if (pattern instanceof CellPattern) {
      CellPattern cellPattern = (CellPattern) pattern;
      cellPattern = substitute(cellPattern.car()).consOnto(substitute(cellPattern.cdr()));
      return (Pattern<T>) cellPattern;

    } else {
      throw new IllegalArgumentException();
    }
  }
}
