package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

public interface Pattern<T extends LexicalItem> {
  default CellPattern consOnto(Pattern<?> item) {
    return new CellPattern(this, item);
  }
}
