package org.topiello.grammar;

import java.util.stream.Stream;

public interface Rule<T> {
  T variable();

  Stream<T> products();

  int length();

  T product(int index);
}
