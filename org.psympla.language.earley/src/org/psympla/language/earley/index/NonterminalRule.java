package org.psympla.language.earley.index;

import java.util.List;

import org.psympla.constraint.Scope;
import org.psympla.grammar.Rule;
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
public class NonterminalRule extends IndexedRule {
  protected NonterminalRule(int index, Rule rule) {
    super(index, rule.pattern(), rule.pro, scope);
    this.pattern = pattern;
    this.products = List.copyOf(products);
    this.scope = scope;
    this.index = index;
  }

  @Override
  public int index() {
    return index;
  }

  @Override
  public Pattern pattern() {
    return pattern;
  }

  @Override
  public IndexedProduct getProduct(int index) {
    return products.get(index);
  }

  @Override
  public int getProductCount() {
    return products.size();
  }

  @Override
  public IndexedProduct getItems(int index) {
    return products.get(index);
  }

  /*
   * 
   * 
   * 
   * 
   * TODO there is one more item than products, the one wit hthe dot at the end...
   * 
   * 
   * 
   * 
   */
  @Override
  public int getItemCount() {
    return products.size();
  }

  @Override
  public Scope scope() {
    return scope;
  }
}
