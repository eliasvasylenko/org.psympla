package org.topiello.earley;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.topiello.ast.ItemNode;

public class EarleySet {
  private final int inputPosition;

  /*
   * Closure of null-predicted items, i.e. items with start position at the
   * current input position.
   */
  private final BitSet predictedNonterminals;
  private final BitSet predictedTerminals;

  /*
   * Items from scanning or completion, with start position earlier to the current
   * input position.
   */
  private final Map<ItemNode, EarleyItem> items;

  public EarleySet(int inputPosition) {
    this.inputPosition = inputPosition;
    this.items = new HashMap<>();

    this.predictedNonterminals = new BitSet();
    this.predictedTerminals = new BitSet();
  }

  public int inputPosition() {
    return inputPosition;
  }

  EarleyItem getItem(ItemNode lr0Item) {
    return items.computeIfAbsent(lr0Item, EarleyItem::new);
  }

  public Stream<EarleyItem> nodes() {
    return items.values().stream();
  }
}
