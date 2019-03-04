package org.psympla.semantics;

import java.util.stream.Stream;

import org.psympla.symbol.LexicalItem;

public interface DecodeState {
  <T> T get(Sign<T> signifier);

  <T> T get(Sign<T> signifier, int index);

  <T> Stream<T> getRemaining(Sign<T> signifier);

  <T> Stream<T> getAll(Sign<T> signifier);

  <U> U getContext(Class<U> type);

  <U extends LexicalItem> U getInstantiation(Unknown<U> pattern);
}
