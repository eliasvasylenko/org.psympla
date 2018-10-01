package org.psympla.symbol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PartialInstantiations {
  private final Map<Variable<?>, ? extends Pattern<?>> instantiations;

  protected PartialInstantiations() {
    instantiations = Collections.emptyMap();
  }

  PartialInstantiations(Map<Variable<?>, ? extends Pattern<?>> instantiations) {
    this.instantiations = instantiations;
  }

  Map<Variable<?>, ? extends Pattern<?>> getInstantiations() {
    return instantiations;
  }

  public Stream<Variable<?>> variables() {
    return instantiations.keySet().stream();
  }

  @SuppressWarnings("unchecked")
  public <T extends LexicalItem<T>> Pattern<T> instantiate(Variable<T> variable) {
    var instantiation = instantiations.get(variable);
    if (instantiation == null) {
      return variable;
    }
    return (Pattern<T>) instantiation;
  }

  public <T extends LexicalItem<T>> PartialInstantiations with(
      Variable<T> variable,
      Pattern<T> pattern) {
    Map<Variable<?>, Pattern<?>> instantiations = new HashMap<>(this.instantiations);
    instantiations.put(variable, pattern);
    return new PartialInstantiations(instantiations);
  }
}
