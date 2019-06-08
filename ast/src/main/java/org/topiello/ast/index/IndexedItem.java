package org.topiello.ast.index;

import java.util.BitSet;
import java.util.Set;

import org.topiello.ast.ItemNode;

public class IndexedItem {
  private final ItemNode item;

  private boolean nullScanned;

  private boolean nullCompletable;
  private final Set<ItemNode> nullReachableItems;
  private final BitSet nullReachableTerminals;

  private IndexedItem(
      ItemNode item,
      boolean nullCompletable,
      Set<ItemNode> nullReachableItems,
      BitSet nullReachableTerminals) {
    this.item = item;

    this.nullCompletable = nullCompletable;
    this.nullReachableItems = nullReachableItems;
    this.nullReachableTerminals = nullReachableTerminals;

    this.nullScanned = false;
  }

  public ItemNode item() {
    return item;
  }

  public Set<ItemNode> nullReachableItems() {
    return nullReachableItems;
  }

  public BitSet nullScannableLexicalClasses() {
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
