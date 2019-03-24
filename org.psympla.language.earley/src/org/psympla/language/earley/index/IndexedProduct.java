package org.psympla.language.earley.index;

import org.psympla.pattern.Pattern;

//TODO value type & record
public class IndexedProduct {
  private final LR0Item index;
  private final Pattern pattern;

  public IndexedProduct(LR0Item item, Pattern pattern) {
    this.index = item;
    this.pattern = pattern;
  }

  public LR0Item index() {
    return index;
  }

  public Pattern pattern() {
    return pattern;
  }
}
