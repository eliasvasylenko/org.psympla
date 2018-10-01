package org.psympla.symbol;

import java.util.Optional;
import java.util.stream.Stream;

public class Variable<T extends LexicalItem<T>> implements Pattern<T> {
  private final String name;

  /*
   * We can't allow access to the constructor, as we need to ensure that we can't
   * instantiate with an explicit type parameter.
   */
  private Variable(String name) {
    this.name = name;
  }

  public static Variable<?> named(String name) {
    return new Variable<>(name);
  }

  public <U extends LexicalItem<U>> Variable<U> typed(Class<U> type) {
    // TODO Auto-generated method stub
    return null;
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
  public Pattern<T> partiallyConstruct(PartialInstantiations instantiations) {
    return instantiations.instantiate(this);
  }

  @Override
  public Optional<Instantiations> partiallyDestructure(Pattern<T> pattern) {
    // TODO Auto-generated method stub
    return null;
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
