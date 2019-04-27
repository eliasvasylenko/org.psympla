package org.topiello.grammar.contextfree;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.topiello.grammar.Rule;

public class ContextFreeRule implements Rule<Symbol> {
  private final Symbol variable;
  private final List<ContextFreeProduct> products;

  public ContextFreeRule(Symbol variable) {
    this(variable, List.of());
  }

  protected ContextFreeRule(Symbol variable, List<ContextFreeProduct> products) {
    this.variable = variable;
    this.products = products;
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
  public ContextFreeProduct product(int index) {
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
    return new ContextFreeRule(
        variable,
        Stream
            .concat(products.stream(), production.stream().map(ContextFreeProduct::new))
            .collect(toList()));
  }

  @Override
  public String toString() {
    return variable + " -> " + products.stream().map(Object::toString).collect(joining(" "));
  }
}
