package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

public interface Pattern<T extends LexicalItem> {
  default <U extends LexicalItem> CellPattern consOnto(Pattern<U> item) {
    return new CellPattern(this, item);
  }

  T instantiate();
}
