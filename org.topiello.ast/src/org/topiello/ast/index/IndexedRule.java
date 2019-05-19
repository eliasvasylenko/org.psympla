package org.topiello.ast.index;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.concat;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.topiello.grammar.Product;
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
public class IndexedRule<T extends Product> {
  private final int index;

  private final Rule<T> rule;

  private final List<IndexedItem> items;

  IndexedRule(int index, IndexedLanguage<T, ?> indexedLanguage, Rule<T> rule) {
    this.index = index;

    this.rule = rule;

    this.items = concat(
        IntStream.range(0, rule.length()).mapToObj(i -> new IndexedItem(indexedLanguage, this, i)),
        Stream.of(IndexedItem.complete(indexedLanguage, this))).collect(toList());
  }

  public int index() {
    return index;
  }

  public Rule<T> rule() {
    return rule;
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

  public Product product(int index) {
    return rule.product(index);
  }

  public int productCount() {
    return rule.length();
  }

  public Stream<Product> products() {
    return range(0, productCount()).mapToObj(rule::product);
  }
}
