package org.psympla.language;

import java.util.List;

import org.psympla.language.Language;
import org.psympla.semantics.Context;
import org.psympla.semantics.Sign;

public interface Language<C> {
  Language<C> language();

  <T> T decode(Sign<T> sign, List<C> encoding, Context context);

  <T> List<C> encode(Sign<T> sign, T information, Context context);
}
