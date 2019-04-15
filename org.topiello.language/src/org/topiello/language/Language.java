package org.topiello.language;

import org.topiello.semantics.Sign;
import org.topiello.text.TextUnit;

public interface Language<T, C extends TextUnit> {
  <U> Designation<C, U> designation(Sign<U> sign);
}
