package org.psympla.language.earley.index;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
  private final List<IndexedItem> items;
  private final List<IndexedProduct> products;
  private final Scope scope;

  IndexedRule(
      int index,
      IndexedLanguage<?> indexedLanguage,
      Pattern pattern,
      List<Pattern> products,
      Scope scope) {
    this.pattern = pattern;
    this.items = IntStream
        .rangeClosed(0, products.size())
        .mapToObj(i -> new IndexedItem(indexedLanguage, new LR0Item(this, i)))
        .collect(toList());
    this.products = IntStream
        .range(0, products.size())
        .mapToObj(i -> new IndexedProduct(new LR0Item(this, i), products.get(i)))
        .collect(toList());
    this.scope = scope;
    this.index = index;
  }

  public int index() {
    return index;
  }

  public Pattern pattern() {
    return pattern;
  }

  public IndexedItem getItem(int index) {
    return items.get(index);
  }

  public int getItemCount() {
    return items.size();
  }

  public Stream<IndexedItem> getItems() {
    return items.stream();
  }

  public IndexedProduct getProduct(int index) {
    return products.get(index);
  }

  public int getProductCount() {
    return products.size();
  }

  public Stream<IndexedProduct> getProducts() {
    return products.stream();
  }

  public Scope scope() {
    return scope;
  }
}
