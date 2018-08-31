package org.psympla.grammar;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.psympla.grammar.InstantiationMissingException;
import org.psympla.grammar.LexicalItem;
import org.psympla.grammar.PartialInstantiations;
import org.psympla.grammar.Pattern;
import org.psympla.grammar.Variable;

public class PartialInstantiations {
  private final Map<Variable<?>, Pattern<?>> instantiations;

  protected PartialInstantiations() {
    instantiations = Collections.emptyMap();
  }

  protected Map<Variable<?>, Pattern<?>> getInstantiations() {
    return instantiations;
  }

  protected PartialInstantiations(Map<Variable<?>, Pattern<?>> instantiations) {
    this.instantiations = instantiations;
  }

  public Stream<Variable<?>> variables() {
    return instantiations.keySet().stream();
  }

  @SuppressWarnings("unchecked")
  <T extends LexicalItem<T>> Pattern<T> instantiate(Variable<T> variable) {
    var instantiation = instantiations.get(variable);
    if (instantiation == null) {
      throw new InstantiationMissingException(variable);
    }
    return (Pattern<T>) instantiation;
  }

  static PartialInstantiations empty() {
    return new PartialInstantiations();
  }

  <T extends LexicalItem<T>> PartialInstantiations with(
      Variable<T> variable,
      Pattern<T> pattern) {
    Map<Variable<?>, Pattern<?>> instantiations = new HashMap<>(getInstantiations());
    instantiations.put(variable, pattern);
    return new PartialInstantiations(instantiations);
  }
}
