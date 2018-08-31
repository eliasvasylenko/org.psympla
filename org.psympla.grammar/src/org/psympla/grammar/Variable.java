package org.psympla.grammar;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Variable<T extends LexicalItem<T>> implements Pattern<T> {
  private final String name;

  public Variable(String name, Supplier<? extends T> anonymous) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public T construct(Instantiations instantiations) {
    return instantiations.instantiate(this);
  }

  @Override
  public Optional<Instantiations> destructure(T symbol) {
    return Optional.of(Instantiations.empty().with(this, symbol));
  }

  @Override
  public Stream<Variable<?>> getVariables() {
    return Stream.of(this);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + getName() + ")";
  }
}
