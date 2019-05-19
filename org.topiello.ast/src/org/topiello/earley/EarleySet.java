package org.topiello.earley;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.topiello.ast.index.IndexedLanguage;
import org.topiello.ast.index.LR0Item;
import org.topiello.ast.index.RuleSet;
import org.topiello.ast.index.TerminalSet;

public class EarleySet {
  private final int inputPosition;

  /*
   * Closure of null-predicted items, i.e. items with start position at the
   * current input position.
   */
  private final RuleSet<?> predictedNonterminals;
  private final TerminalSet<?> predictedTerminals;

  /*
   * Items from scanning or completion, with start position earlier to the current
   * input position.
   */
  private final Map<LR0Item, EarleyItem> items;

  public EarleySet(IndexedLanguage<?, ?> language, int inputPosition) {
    this.inputPosition = inputPosition;
    this.items = new HashMap<>();

    this.predictedNonterminals = language.createRuleSet();
    this.predictedTerminals = language.createTerminalSet();
  }

  public int inputPosition() {
    return inputPosition;
  }

  EarleyItem getItem(LR0Item lr0Item) {
    return items.computeIfAbsent(lr0Item, EarleyItem::new);
  }

  public Stream<EarleyItem> nodes() {
    return items.values().stream();
  }
}
