package org.psympla.symbol;

public interface LexicalItem {
  default <T extends Sequence> Cell<?, T> consOnto(T item) {
    return new Cell<>(this, item);
  }
}
