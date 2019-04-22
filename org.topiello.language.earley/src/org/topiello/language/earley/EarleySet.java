package org.topiello.language.earley;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.topiello.language.earley.index.LR0Item;
import org.topiello.language.earley.index.NonterminalRuleSet;
import org.topiello.language.earley.index.TerminalRuleSet;

public class EarleySet {
  private final int inputPosition;

  /*
   * Closure of null-predicted items, i.e. items with start position at the
   * current input position.
   */
  private final NonterminalRuleSet<?> predictedNonterminals;
  private final TerminalRuleSet<?, ?> predictedTerminals;

  /*
   * Items from scanning or completion, with start position earlier to the current
   * input position.
   */
  private final Map<LR0Item, EarleyItem> items;

  public EarleySet(int inputPosition) {
    this.inputPosition = inputPosition;
    this.items = new HashMap<>();
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
