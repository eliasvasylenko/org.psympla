package org.psympla.language;

import org.psympla.semantics.Sign;

public interface Language<C> {
  <T> Designation<C, T> designation(Sign<T> sign);
}
