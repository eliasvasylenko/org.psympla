package org.topiello.pattern.symbol;

public class Value<T> implements Atom<T> {
  private final T data;

  public Value(T data) {
    this.data = data;
  }

  public T get() {
    return data;
  }

  @Override
  public <U extends Sequence> Cell<Value<T>, U> consOnto(U item) {
    return new Cell<>(this, item);
  }
}
