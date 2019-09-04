package org.topiello.lexer;

import java.util.Set;

public interface Lexer<T> {
  void advanceTo(long inputPosition);

  boolean scan(T terminal);

  Set<T> scan(Set<T> terminals);

  Token<T, ?> getToken(T terminal);
}
