package org.psympla.symbol;

public class Value<T> implements Atom<T> {
  private final T data;

  public Value(T data) {
    this.data = data;
  }

  public T get() {
    return data;
  }
}
