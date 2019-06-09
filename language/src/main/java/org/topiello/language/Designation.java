/*
 * Topiello Language - The Topiello language API
 * Copyright © 2018 Strange Skies (elias@vasylenko.uk)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
