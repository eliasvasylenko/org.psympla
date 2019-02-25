package org.psympla.language;

import org.psympla.lexicon.Sequence;
import org.psympla.semantics.Context;
import org.psympla.semantics.Sign;

public interface Designation<C, T> {
  Sign<T> sign();

  default T decode(Sequence<C> encoding) {
    return decode(encoding, Context.empty());
  }

  T decode(Sequence<C> encoding, Context context);

  default Sequence<C> encode(T information) {
    return encode(information, Context.empty());
  }

  Sequence<C> encode(T information, Context context);
}
