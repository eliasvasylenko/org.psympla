/*
 * Topiello Text - The text API
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
package org.topiello.text;

import java.util.Iterator;
import java.util.function.Function;

public abstract class CharacterConverter<C extends TextUnit, D extends TextUnit> {
  private final CharacterSet<D> characterSet;

  public CharacterConverter(CharacterSet<D> characterSet) {
    this.characterSet = characterSet;
  }

  public CharacterSet<D> characterSet() {
    return characterSet;
  }

  public abstract Iterator<D> dissemble(C character);

  public abstract C assemble(Iterator<D> characters);

  public static <C extends TextUnit, D extends TextUnit> CharacterConverter<C, D> toAndFrom(
      CharacterSet<D> characterSet,
      Function<C, Iterator<D>> dissembleTo,
      Function<Iterator<D>, C> assembleFrom) {
    return new CharacterConverter<>(characterSet) {
      @Override
      public Iterator<D> dissemble(C character) {
        return dissembleTo.apply(character);
      }

      @Override
      public C assemble(Iterator<D> characters) {
        return assembleFrom.apply(characters);
      }
    };
  }
}
