package org.topiello.language;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

/**
 * A designation is the mechanism of encoding and object to, and decoding it
 * from, its signifier {@link Text text}. That is, it is an implementation of a
 * {@link Sign sign} relation.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 *          the character representation of the signifier text
 * @param <T>
 *          the type of the signified object
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
