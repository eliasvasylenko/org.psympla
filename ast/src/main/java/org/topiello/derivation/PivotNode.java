package org.topiello.derivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO value type and record
public class PivotNode {
  private final int pivot;
  private final ParseNode adjacent;
  private final List<ParseNode> derivations;

  PivotNode(int pivot, ParseNode adjacent) {
    this.pivot = pivot;
    this.adjacent = adjacent;
    this.derivations = new ArrayList<>();
  }

  public int pivot() {
    return pivot;
  }

  public ParseNode adjacent() {
    return adjacent;
  }

  public List<ParseNode> derivations() {
    return Collections.unmodifiableList(derivations);
  }
}
