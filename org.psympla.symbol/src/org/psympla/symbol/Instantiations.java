package org.psympla.symbol;

import java.util.HashMap;
import java.util.Map;

public class Instantiations extends PartialInstantiations {
  protected Instantiations() {}

  Instantiations(Map<Variable<?>, LexicalItem<?>> instantiations) {
    super(instantiations);
  }

  @SuppressWarnings("unchecked")
  @Override
  Map<Variable<?>, LexicalItem<?>> getInstantiations() {
    return (Map<Variable<?>, LexicalItem<?>>) super.getInstantiations();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends LexicalItem<T>> T instantiate(Variable<T> variable) {
    return (T) super.instantiate(variable);
  }

  public static Instantiations empty() {
    return new Instantiations();
  }

  public <T extends LexicalItem<T>> Instantiations with(Variable<T> variable, T pattern) {
    Map<Variable<?>, LexicalItem<?>> instantiations = new HashMap<>(getInstantiations());
    instantiations.put(variable, pattern);
    return new Instantiations(instantiations);
  }
}
