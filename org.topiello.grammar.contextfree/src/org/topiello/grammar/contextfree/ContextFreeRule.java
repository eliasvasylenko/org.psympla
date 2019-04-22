package org.topiello.grammar.contextfree;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.topiello.grammar.Rule;

public class ContextFreeRule implements Rule<Symbol> {
  private final Symbol variable;
  private final List<Symbol> products;

  public ContextFreeRule(Symbol variable) {
    this(variable, List.of());
  }

  protected ContextFreeRule(Symbol variable, List<Symbol> products) {
    this.variable = variable;
    this.products = List.copyOf(products);
  }

  @Override
  public Symbol variable() {
    return variable;
  }

  @Override
  public int length() {
    return products.size();
  }

  @Override
  public Symbol product(int index) {
    return products.get(index);
  }

  public ContextFreeRule withProduct(Symbol product) {
    return withProducts(product);
  }

  @SafeVarargs
  public final ContextFreeRule withProducts(Symbol... production) {
    return withProducts(List.of(production));
  }

  public ContextFreeRule withProducts(Collection<? extends Symbol> production) {
    List<Symbol> newProduction = new ArrayList<>(this.products.size() + production.size());
    newProduction.addAll(this.products);
    newProduction.addAll(production);
    return new ContextFreeRule(variable, newProduction);
  }

  @Override
  public String toString() {
    return variable + " -> " + products.stream().map(Object::toString).collect(joining(" "));
  }
}
