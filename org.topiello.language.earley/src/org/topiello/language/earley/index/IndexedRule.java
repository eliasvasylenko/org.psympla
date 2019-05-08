package org.topiello.language.earley.index;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.topiello.grammar.Rule;

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
public class IndexedRule<T extends Rule<?>> {
  private final IndexedLanguage<T, ?> indexedLanguage;
  private final int index;

  private final Object variable;

  private final List<IndexedProduct<?>> products;
  private final List<IndexedItem> items;

  IndexedRule(int index, IndexedLanguage<T, ?> indexedLanguage, T rule) {
    this.indexedLanguage = indexedLanguage;
    this.index = index;

    this.variable = rule.variable();

    this.products = IntStream
        .range(0, rule.length())
        .mapToObj(i -> new IndexedProduct<>(new LR0Item(this, i), rule.product(i)))
        .collect(toList());

    this.items = concat(
        IntStream.range(0, rule.length()).mapToObj(i -> new IndexedItem(this, i)),
        Stream.of(IndexedItem.complete(this))).collect(toList());
  }

  public IndexedLanguage<?, ?> language() {
    return indexedLanguage;
  }

  public int index() {
    return index;
  }

  public Object variable() {
    return variable;
  }

  public IndexedItem item(int index) {
    return items.get(index);
  }

  public int itemCount() {
    return items.size();
  }

  public Stream<IndexedItem> items() {
    return items.stream();
  }

  public IndexedProduct<?> product(int index) {
    return products.get(index);
  }

  public int productCount() {
    return products.size();
  }

  public Stream<IndexedProduct<?>> products() {
    return products.stream();
  }
}
