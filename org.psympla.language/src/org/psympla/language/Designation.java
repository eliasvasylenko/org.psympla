package org.psympla.language;

import org.psympla.semantics.Context;
import org.psympla.semantics.Sign;
import org.psympla.text.Text;

public interface Designation<C, T> {
  Sign<T> sign();

  default T decode(Text<C> encoding) {
    return decode(encoding, Context.empty());
  }

  T decode(Text<C> encoding, Context context);

  default Text<C> encode(T information) {
    return encode(information, Context.empty());
  }

  Text<C> encode(T information, Context context);
}
