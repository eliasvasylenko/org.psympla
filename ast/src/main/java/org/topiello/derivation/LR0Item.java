package org.topiello.derivation;

import org.topiello.grammar.Rule;

// TODO value & record type
public class LR0Item {
  private final Rule<?> rule;
  private final int dotPosition;

  public LR0Item(Rule<?> rule, int dotPosition) {
    this.rule = rule;
    this.dotPosition = dotPosition;
  }

  public Rule<?> rule() {
    return rule;
  }

  public int dotPosition() {
    return dotPosition;
  }
}
