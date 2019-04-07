package org.topiello.language;

import org.topiello.semantics.Context;
import org.topiello.semantics.Sign;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface Designation<C extends TextUnit, T> {
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
