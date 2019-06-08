package org.topiello.grammar;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 *          the type attached to the node that appears in the parse tree for
 *          this rule.
 */
public class Rule<T extends Product> {
  private final Variable variable;
  private final List<T> products;

  @SafeVarargs
  public Rule(Variable variable, T... products) {
    this.variable = variable;
    this.products = Arrays.asList(products);
  }

  public Rule(Variable variable, Collection<? extends T> products) {
    this.variable = variable;
    this.products = List.copyOf(products);
  }

  public Variable variable() {
    return variable;
  }

  public int length() {
    return products.size();
  }

  public T product(int index) {
    return products.get(index);
  }
}
