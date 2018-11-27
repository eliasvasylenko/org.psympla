package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

public class Matches<T extends LexicalItem> extends Constraint<T> {
  private final Pattern<T> pattern;

  public Matches(Pattern<T> pattern) {
    this.pattern = pattern;
  }
}
