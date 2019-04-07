package org.topiello.language;

import org.topiello.semantics.Sign;
import org.topiello.text.TextUnit;

public interface Language<C extends TextUnit> {
  <T> Designation<C, T> designation(Sign<T> sign);
}
