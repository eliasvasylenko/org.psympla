package org.topiello.grammar;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class ContextFreeRule<T> implements Rule<T> {
  private final T variable;
  private final List<T> products;

  public ContextFreeRule(T variable) {
    this(variable, List.of());
  }

  protected ContextFreeRule(T variable, List<T> products) {
    this.variable = variable;
    this.products = List.copyOf(products);
  }

  @Override
  public T variable() {
    return variable;
  }

  @Override
  public Stream<T> products() {
    return products.stream();
  }

  @Override
  public int length() {
    return products.size();
  }

  @Override
  public T product(int index) {
    return products.get(index);
  }

  public Rule<T> withProduct(T product) {
    return withProducts(product);
  }

  @SafeVarargs
  public final Rule<T> withProducts(T... production) {
    return withProducts(List.of(production));
  }

  public Rule<T> withProducts(Collection<? extends T> production) {
    List<T> newProduction = new ArrayList<>(this.products.size() + production.size());
    newProduction.addAll(this.products);
    newProduction.addAll(production);
    return new ContextFreeRule<>(variable, newProduction);
  }

  @Override
  public String toString() {
    return variable + " -> " + products.stream().map(Object::toString).collect(joining(" "));
  }
}
