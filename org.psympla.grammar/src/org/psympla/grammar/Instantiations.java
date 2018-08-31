package org.psympla.grammar;

import java.util.HashMap;
import java.util.Map;

import org.psympla.grammar.Instantiations;
import org.psympla.grammar.LexicalItem;
import org.psympla.grammar.PartialInstantiations;
import org.psympla.grammar.Pattern;
import org.psympla.grammar.Variable;

public class Instantiations extends PartialInstantiations {
  protected Instantiations() {}

  protected Instantiations(Map<Variable<?>, Pattern<?>> instantiations) {
    super(instantiations);
  }

  @SuppressWarnings("unchecked")
  <T extends LexicalItem<T>> T instantiate(Variable<T> variable) {
    return (T) super.instantiate(variable);
  }

  static Instantiations empty() {
    return new Instantiations();
  }

  <T extends LexicalItem<T>> Instantiations with(Variable<T> variable, T symbol) {
    return null;
  }

  <T extends LexicalItem<T>> Instantiations with(Variable<T> variable, Pattern<T> pattern) {
    Map<Variable<?>, Pattern<?>> instantiations = new HashMap<>(getInstantiations());
    instantiations.put(variable, pattern);
    return new Instantiations(instantiations);
  }
}
