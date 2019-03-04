package org.psympla.symbol;

import java.util.stream.Stream;

public class Nil implements Sequence, Atom<Void> {
  static final Nil INSTANCE = new Nil();

  private Nil() {}

  @Override
  public Stream<LexicalItem> elements() {
    return Stream.empty();
  }

  @Override
  public <T extends Sequence> Cell<Nil, T> consOnto(T item) {
    return new Cell<>(this, item);
  }
}
