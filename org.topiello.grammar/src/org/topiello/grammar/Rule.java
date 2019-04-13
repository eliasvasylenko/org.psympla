package org.topiello.grammar;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Rule<T> {
  private final T T;
  private final List<T> products;

  public Rule(T T) {
    this(T, List.of());
  }

  protected Rule(T T, List<T> products) {
    this.T = T;
    this.products = List.copyOf(products);
  }

  public T T() {
    return T;
  }

  public Stream<T> products() {
    return products.stream();
  }

  public int length() {
    return products.size();
  }

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
    return new Rule<>(T, newProduction);
  }

  @Override
  public String toString() {
    return T + " -> " + products.stream().map(Object::toString).collect(joining(" "));
  }
}
