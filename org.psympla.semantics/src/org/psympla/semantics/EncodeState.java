package org.psympla.semantics;

import java.util.Collection;

import org.psympla.symbol.LexicalItem;

public interface EncodeState<T> {
  <U> EncodeState<T> put(Sign<U> signifier, U information);

  <U> EncodeState<T> putAll(Sign<U> signifier, @SuppressWarnings("unchecked") U... information);

  <U> EncodeState<T> putAll(Sign<U> signifier, Collection<? extends U> information);

  EncodeState<T> setParameter(LexicalItem<?> parameter);

  <U> U getContext(Class<U> type);
}
