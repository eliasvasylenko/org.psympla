package org.psympla.language.earley.index;

import static java.util.Collections.emptySet;
import static org.psympla.language.earley.index.Nullability.NON_NULLABLE;
import static org.psympla.language.earley.index.Nullability.NULL_COMPLETABLE;
import static org.psympla.language.earley.index.Nullability.NULL_SCANNABLE;

import java.util.HashSet;
import java.util.Set;

import org.psympla.text.Text;

public class IndexedItem {
  private final LR0Item item;

  private boolean nullScanned;
  private Set<LR0Item> predictors;

  private Nullability nullability;
  private final Set<LR0Item> nullReachableItems;
  private final TerminalRuleSet<?> nullReachableTerminals;

  private IndexedItem(
      LR0Item item,
      Nullability nullability,
      Set<LR0Item> nullReachableItems,
      TerminalRuleSet<?> nullReachableTerminals) {
    this.item = item;

    this.nullability = nullability;
    this.nullReachableItems = nullReachableItems;
    this.nullReachableTerminals = nullReachableTerminals;

    this.nullScanned = false;
    this.predictors = emptySet();
  }

  static IndexedItem nonterminal(NonterminalRule rule, int dotPosition) {
    return new IndexedItem(
        new LR0Item(rule, dotPosition),
        Nullability.NON_NULLABLE,
        new HashSet<>(),
        rule.language().createTerminalRuleSet());
  }

  static IndexedItem terminal(TerminalRule<?> rule) {
    boolean nullable = rule.lexicalClass().scan(Text.empty()).findAny().isPresent();

    return new IndexedItem(
        new LR0Item(rule, 0),
        nullable ? NULL_COMPLETABLE : NON_NULLABLE,
        nullable ? Set.of(new LR0Item(rule, 1)) : emptySet(),
        rule.language().createTerminalRuleSet());
  }

  static IndexedItem complete(IndexedRule rule) {
    return new IndexedItem(
        new LR0Item(rule, rule.productCount()),
        NULL_COMPLETABLE,
        emptySet(),
        rule.language().createTerminalRuleSet());
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

  public TerminalRuleSet<?> nullReachableTerminals() {
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
