package org.topiello.language;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

/**
 * A designation is a mapping between a "signifier" and a piece of text which
 * represents it in some {@link Language}. A signifier is simply a Java Object.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 * @param <T>
 */
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
