package org.psympla.language.earley.index;

import java.util.List;

import org.psympla.constraint.Scope;
import org.psympla.pattern.Pattern;

/**
 * A synthetic rule representing a lexical class. The LHS is the terminal symbol
 * which matches the lexical class, and the RHS is a variable intended to be
 * instantiated to the input text which is successfully parsed.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
// TODO value type?
public abstract class IndexedRule {
  private final int index;
  private final Pattern pattern;
  private final List<IndexedProduct> products;
  private final Scope scope;

  protected IndexedRule(int index, Pattern pattern, List<Pattern> products, Scope scope) {
    this.pattern = pattern;
    this.products = List.copyOf(products);
    this.scope = scope;
    this.index = index;
  }

  public int index() {
    return index;
  }

  public Pattern pattern() {
    return pattern;
  }

  public IndexedProduct getProduct(int index) {
    return products.get(index);
  }

  public int getProductCount() {
    return products.size();
  }

  public Scope scope() {
    return scope;
  }
}
