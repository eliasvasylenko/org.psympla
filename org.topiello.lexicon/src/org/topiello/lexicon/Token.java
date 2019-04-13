package org.topiello.lexicon;

public class Token<T> {
  private final T value;

  public Token(T value) {
    this.value = value;
  }

  public T value() {
    return value;
  }
}
