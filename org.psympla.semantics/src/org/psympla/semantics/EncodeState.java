package org.psympla.semantics;

import java.util.Collection;

import org.psympla.pattern.Substitution;
import org.psympla.pattern.Variable;
import org.psympla.symbol.LexicalItem;

public interface EncodeState {
  <U> U getContext(Class<U> type);

  <U> EncodeState put(Sign<U> signifier, U information);

  <U> EncodeState putAll(Sign<U> signifier, @SuppressWarnings("unchecked") U... information);

  <U> EncodeState putAll(Sign<U> signifier, Collection<? extends U> information);

  EncodeState setParameter(LexicalItem parameter);

  EncodeState instantiations(Substitution instantiations);

  <U extends LexicalItem> EncodeState putInstantiation(Variable<U> variable, U instantiation);
}
