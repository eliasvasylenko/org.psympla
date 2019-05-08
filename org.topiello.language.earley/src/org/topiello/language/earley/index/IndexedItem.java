package org.topiello.language.earley.index;

import static java.util.Collections.emptySet;
import static org.topiello.language.earley.index.Nullability.NON_NULLABLE;
import static org.topiello.language.earley.index.Nullability.NULL_COMPLETABLE;
import static org.topiello.language.earley.index.Nullability.NULL_SCANNABLE;

import java.util.HashSet;
import java.util.Set;

import org.topiello.text.Text;

public class IndexedItem {
  private final LR0Item item;

  private boolean nullScanned;
  private Set<LR0Item> predictors;

  private Nullability nullability;
  private final Set<LR0Item> nullReachableItems;
  private final RuleSet<?> nullReachableTerminals;

  private IndexedItem(
      LR0Item item,
      Nullability nullability,
      Set<LR0Item> nullReachableItems,
      RuleSet<?> nullReachableTerminals) {
    this.item = item;

    this.nullability = nullability;
    this.nullReachableItems = nullReachableItems;
    this.nullReachableTerminals = nullReachableTerminals;

    this.nullScanned = false;
    this.predictors = emptySet();
  }

  IndexedItem(IndexedRule<?> rule, int dotPosition) {
    this(
        new LR0Item(rule, dotPosition),
        Nullability.NON_NULLABLE,
        new HashSet<>(),
        rule.language().createRuleSet());
  }

  static IndexedItem complete(IndexedRule<?> rule) {
    return new IndexedItem(
        new LR0Item(rule, rule.productCount()),
        NULL_COMPLETABLE,
        emptySet(),
        rule.language().createRuleSet());
  }

  public LR0Item item() {
    return item;
  }

  void markNullable() {
    switch (item.rule().item(item.dotPosition() + 1).nullability()) {
    case NULL_COMPLETABLE:
      nullability = NULL_COMPLETABLE;
    default:
      nullability = NULL_SCANNABLE;
    }
  }

  public Set<LR0Item> nullReachableItems() {
    return nullReachableItems;
  }

  public TerminalRuleSet<?, ?> nullReachableTerminals() {
    return nullReachableTerminals;
  }

  /**
   * @return true if the predecessor of the item is
   *         {@link Nullability#NULL_SCANNABLE null scannable}.
   */
  public boolean isNullScanned() {
    return nullScanned;
  }

  public Nullability nullability() {
    return nullability;
  }
}
