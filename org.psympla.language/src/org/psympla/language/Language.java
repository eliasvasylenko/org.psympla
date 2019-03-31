package org.psympla.language;

import org.psympla.semantics.Sign;
import org.psympla.text.TextUnit;

public interface Language<C extends TextUnit> {
  <T> Designation<C, T> designation(Sign<T> sign);
}
