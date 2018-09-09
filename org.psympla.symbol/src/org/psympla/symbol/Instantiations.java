package org.psympla.symbol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Instantiations {
  private final Map<Variable<?>, LexicalItem<?>> instantiations;

  protected Instantiations() {
    instantiations = Collections.emptyMap();
  }

  private Instantiations(Map<Variable<?>, LexicalItem<?>> instantiations) {
    this.instantiations = instantiations;
  }

  public Stream<Variable<?>> variables() {
    return instantiations.keySet().stream();
  }

  @SuppressWarnings("unchecked")
  public <T extends LexicalItem<T>> T instantiate(Variable<T> variable) {
    var instantiation = instantiations.get(variable);
    if (instantiation == null) {
      throw new InstantiationMissingException(variable);
    }
    return (T) instantiation;
  }

  public static Instantiations empty() {
    return new Instantiations();
  }

  public <T extends LexicalItem<T>> Instantiations with(Variable<T> variable, T pattern) {
    Map<Variable<?>, LexicalItem<?>> instantiations = new HashMap<>(this.instantiations);
    instantiations.put(variable, pattern);
    return new Instantiations(instantiations);
  }
}
