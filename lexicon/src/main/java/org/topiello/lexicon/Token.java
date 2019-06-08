package org.topiello.lexicon;

public interface Token<T, U> {
  T variable();

  U evaluate();
}
