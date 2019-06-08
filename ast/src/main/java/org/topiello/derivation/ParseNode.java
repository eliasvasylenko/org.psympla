package org.topiello.derivation;

import java.util.TreeMap;

public class ParseNode {
  private final LR0Item item;
  private final int leftExtent;
  private final int rightExtent;
  private final TreeMap<Integer, PivotNode> pivots;

  public ParseNode(LR0Item item, int leftExtent, int rightExtent) {
    this.item = item;
    this.leftExtent = leftExtent;
    this.rightExtent = rightExtent;
    this.pivots = new TreeMap<>();
  }

  public LR0Item item() {
    return item;
  }

  public int leftExtent() {
    return leftExtent;
  }

  public int rightExtent() {
    return rightExtent;
  }

  public PivotNode getPivot(int position) {
    return pivots
        .computeIfAbsent(
            position,
            p -> new PivotNode(
                p,
                item.dotPosition() > 1
                    ? new ParseNode(
                        new LR0Item(item.rule(), item.dotPosition() - 1),
                        leftExtent,
                        position)
                    : null));
  }
}
