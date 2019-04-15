package org.topiello.semantics;

import java.util.Collection;

import org.topiello.language.Sign;

public interface EncodeState {
  <U> U getContext(Class<U> type);

  <U> EncodeState put(Sign<U> signifier, U information);

  <U> EncodeState putAll(Sign<U> signifier, @SuppressWarnings("unchecked") U... information);

  <U> EncodeState putAll(Sign<U> signifier, Collection<? extends U> information);
}
