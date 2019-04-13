package org.topiello.language.earley.index;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.topiello.constraint.Scope;
import org.topiello.grammar.Rule;
import org.topiello.pattern.Pattern;

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
public abstract class IndexedRule<T extends Rule<?>> {
  private final IndexedLanguage<T, ?> indexedLanguage;
  private final int index;

  private final Pattern pattern;

  private final List<IndexedProduct> products;

  IndexedRule(
      int index,
      IndexedLanguage<T, ?> indexedLanguage,
      Pattern pattern,
      List<Pattern> products) {
    this.indexedLanguage = indexedLanguage;
    this.index = index;

    this.pattern = pattern;
    this.products = IntStream
        .range(0, products.size())
        .mapToObj(i -> new IndexedProduct(new LR0Item(this, i), products.get(i)))
        .collect(toList());
  }

  public IndexedLanguage<?, ?> language() {
    return indexedLanguage;
  }

  protected abstract List<IndexedItem> getItems();

  public int index() {
    return index;
  }

  public Pattern pattern() {
    return pattern;
  }

  public IndexedItem item(int index) {
    return getItems().get(index);
  }

  public int itemCount() {
    return getItems().size();
  }

  public Stream<IndexedItem> items() {
    return getItems().stream();
  }

  public IndexedProduct product(int index) {
    return products.get(index);
  }

  public int productCount() {
    return products.size();
  }

  public Stream<IndexedProduct> products() {
    return products.stream();
  }

  public Scope scope() {
    return scope;
  }
}
