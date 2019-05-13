package org.topiello.language.earley.index;

import static java.util.Collections.emptySet;

import java.util.HashSet;
import java.util.Set;

public class IndexedItem {
  private final LR0Item item;

  private boolean nullScanned;
  private Set<LR0Item> predictors;

  private boolean nullCompletable;
  private final Set<LR0Item> nullReachableItems;
  private final TerminalSet<?> nullReachableTerminals;

  private IndexedItem(
      LR0Item item,
      boolean nullCompletable,
      Set<LR0Item> nullReachableItems,
      TerminalSet<?> nullReachableTerminals) {
    this.item = item;

    this.nullCompletable = nullCompletable;
    this.nullReachableItems = nullReachableItems;
    this.nullReachableTerminals = nullReachableTerminals;

    this.nullScanned = false;
    this.predictors = emptySet();
  }

  IndexedItem(IndexedLanguage<?, ?> language, IndexedRule<?> rule, int dotPosition) {
    this(new LR0Item(rule, dotPosition), false, new HashSet<>(), language.createTerminalSet());
  }

  static IndexedItem complete(IndexedLanguage<?, ?> language, IndexedRule<?> rule) {
    return new IndexedItem(
        new LR0Item(rule, rule.rule().length()),
        true,
        emptySet(),
        language.createTerminalSet());
  }

  public LR0Item item() {
    return item;
  }

  void markNullable() {
    nullCompletable = item.rule().item(item.dotPosition() + 1).isNullCompletable();
  }

  public Set<LR0Item> nullReachableItems() {
    return nullReachableItems;
  }

  public TerminalSet<?> nullScannableLexicalClasses() {
    return nullReachableTerminals;
  }

  /**
   * @return true if the predecessor of the item is
   *         {@link Nullability#NULL_SCANNABLE null scannable}.
   */
  public boolean isNullScanned() {
    return nullScanned;
  }

  public boolean isNullCompletable() {
    return nullCompletable;
  }
}
