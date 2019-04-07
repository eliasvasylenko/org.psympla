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
public class NonterminalRule extends IndexedRule {
  private final List<IndexedItem> items;

  NonterminalRule(int index, IndexedLanguage<?> indexedLanguage, Rule rule) {
    super(index, indexedLanguage, rule.pattern(), rule.scope(), rule.products().collect(toList()));

    items = concat(
        IntStream.range(0, productCount()).mapToObj(i -> IndexedItem.nonterminal(this, i)),
        Stream.of(IndexedItem.complete(this))).collect(toList());
  }

  @Override
  protected List<IndexedItem> getItems() {
    return items;
  }
}
