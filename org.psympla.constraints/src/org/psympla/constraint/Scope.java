package org.psympla.constraint;

import static java.util.stream.Collectors.joining;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.psympla.pattern.Variable;
import org.psympla.symbol.LexicalItem;

public class Scope {
  private static final Scope EMPTY = new Scope();

  private final Set<Constraint> constraints;
  private final Map<Variable, LexicalItem> instantiations;

  private Scope() {
    this.constraints = Set.of();
    this.instantiations = Map.of();
  }

  private Scope(Set<Constraint> constraints, Map<Variable, LexicalItem> instantiations) {
    this.constraints = Set.copyOf(constraints);
    this.instantiations = Map.copyOf(instantiations);
  }

  public static Scope empty() {
    return EMPTY;
  }

  public Scope withConstraint(Constraint constraint) {
    var constraints = new HashSet<>(this.constraints);
    constraints.add(constraint);
    return new Scope(constraints, instantiations);
  }

  public Stream<Constraint> constraints() {
    return constraints.stream();
  }

  public Scope withInstantiation(Variable variable, LexicalItem lexicalItem) {
    var instantiations = new HashMap<>(this.instantiations);
    instantiations.put(variable, lexicalItem);
    return new Scope(constraints, instantiations);
  }

  public <T extends LexicalItem> Optional<T> resolveBinding(Constructor<T> binding) {
    return null;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + constraints.stream().map(Objects::toString).collect(joining(", ", "(", ")"));
  }
}
