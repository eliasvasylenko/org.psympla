package org.psympla.language.earley;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class EarleySet {
  private final int inputPosition;
  private final Map<EarleyItem, EarleyItemNode> items;

  public EarleySet(int inputPosition) {
    this.inputPosition = inputPosition;
    this.items = new HashMap<>();
  }

  public int inputPosition() {
    return inputPosition;
  }

  EarleyItemNode addItem(LR0Item lr0Item, int inputOrigin) {
    return items
        .computeIfAbsent(new EarleyItem(lr0Item, inputOrigin, inputPosition), EarleyItemNode::new);
  }

  public Stream<EarleyItemNode> nodes() {
    return items.values().stream();
  }
}
