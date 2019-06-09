/*
 * Topiello Lexicon - An API for creating simple lexicons to be consumed by grammars
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
package org.topiello.lexicon.scanning;

import java.util.stream.Stream;

import org.topiello.lexicon.Token;
import org.topiello.lexicon.LexicalClass;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

/**
 * A scanner accepts a list of characters and outputs a set of {@link Scan
 * scans} matching those characters. Each such scan represents a contiguous
 * sub-sequence of the accepted characters, starting at index 0, and may be
 * evaluated into a parameter.
 * <p>
 * A scanner is intended to be used in order to define the behavior of a
 * {@link LexicalClass lexical class}, where each scan would be associated with
 * a {@link Token lexeme} of that class.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Scanner<T, C extends TextUnit> {
  Stream<Scan<T>> scan(Text<C> characters);
}
