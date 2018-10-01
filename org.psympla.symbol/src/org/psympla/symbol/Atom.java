package org.psympla.symbol;

public interface Atom<T extends LexicalItem<T>> extends LexicalItem<T> {
  @Override
  default boolean isAtomic() {
    return true;
  }
}
