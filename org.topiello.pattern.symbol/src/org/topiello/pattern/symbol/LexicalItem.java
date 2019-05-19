package org.topiello.pattern.symbol;

public interface LexicalItem {
  default <T extends Sequence> Cell<?, T> consOnto(T item) {
    return new Cell<>(this, item);
  }
}
