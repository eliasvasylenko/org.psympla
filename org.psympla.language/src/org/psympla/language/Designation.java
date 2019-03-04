package org.psympla.language;

import org.psympla.lexicon.Characters;
import org.psympla.semantics.Context;
import org.psympla.semantics.Sign;

public interface Designation<C, T> {
  Sign<T> sign();

  default T decode(Characters<C> encoding) {
    return decode(encoding, Context.empty());
  }

  T decode(Characters<C> encoding, Context context);

  default Characters<C> encode(T information) {
    return encode(information, Context.empty());
  }

  Characters<C> encode(T information, Context context);
}
