package org.psympla.language;

import org.psympla.lexicon.Sequence;
import org.psympla.semantics.Context;
import org.psympla.semantics.Sign;

public interface Language<C> {
  Language<C> language();

  default <T> T decode(Sign<T> sign, Sequence<C> encoding) {
    return decode(sign, encoding, Context.empty());
  }

  <T> T decode(Sign<T> sign, Sequence<C> encoding, Context context);

  default <T> Sequence<C> encode(Sign<T> sign, T information) {
    return encode(sign, information, Context.empty());
  }

  <T> Sequence<C> encode(Sign<T> sign, T information, Context context);
}
