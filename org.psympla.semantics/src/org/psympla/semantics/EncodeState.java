package org.psympla.semantics;

import java.util.Collection;

import org.psympla.symbol.Instantiations;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Variable;

public interface EncodeState {
  <U> U getContext(Class<U> type);

  <U> EncodeState put(Sign<U> signifier, U information);

  <U> EncodeState putAll(Sign<U> signifier, @SuppressWarnings("unchecked") U... information);

  <U> EncodeState putAll(Sign<U> signifier, Collection<? extends U> information);

  EncodeState setParameter(LexicalItem<?> parameter);

  EncodeState instantiations(Instantiations instantiations);

  <U extends LexicalItem<U>> EncodeState putInstantiation(Variable<U> variable, U instantiation);

}
