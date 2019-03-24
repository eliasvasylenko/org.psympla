package org.psympla.language.earley.index;

import static java.util.Collections.emptySet;
import static org.psympla.language.earley.index.Nullability.NON_NULLABLE;
import static org.psympla.language.earley.index.Nullability.NULL_COMPLETABLE;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class IndexedItem {
  private final LR0Item item;

  private boolean nullScanned;
  private Set<LR0Item> predictors;

  private Nullability nullability;
  private Set<LR0Item> nullReachableItems;
  private TerminalRuleSet<?> nullReachableTerminals;

  IndexedItem(IndexedLanguage<?> indexedLanguage, LR0Item item) {
    this.item = item;

    this.nullScanned = false;
    this.predictors = emptySet();

    this.nullability = item.isComplete() ? NULL_COMPLETABLE : NON_NULLABLE;
    this.nullReachableItems = emptySet();
    this.nullReachableTerminals = indexedLanguage.createTerminalRuleSet();
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

  public LR0Item item() {
    return item;
  }

  public Optional<LR0Item> prediction(LR0Item item) {
    return Optional.ofNullable(predictions.get(item));
  }

  public Stream<LR0Item> predictions() {
    return predictions.values().stream();
  }
}
