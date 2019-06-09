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
package org.topiello.lexicon;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface LexicalClass<T extends Token<?, ?>, C extends TextUnit> {
  Stream<Lexeme<T, C>> scan(Text<C> characters);

  Optional<Lexeme<T, C>> print(T token);
}
