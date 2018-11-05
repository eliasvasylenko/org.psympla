package org.psympla.symbol;

public interface LexicalItem {
  default Cell consOnto(LexicalItem item) {
    return new Cell(this, item);
  }
}
