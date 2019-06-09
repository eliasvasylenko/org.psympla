/*
 * Topiello Pattern Symbol - Symbols for pattern matching grammars to match over
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
package org.topiello.pattern.symbol;

import java.util.stream.Stream;

public class Nil implements Sequence, Atom<Void> {
  static final Nil INSTANCE = new Nil();

  private Nil() {}

  @Override
  public Stream<LexicalItem> elements() {
    return Stream.empty();
  }

  @Override
  public <T extends Sequence> Cell<Nil, T> consOnto(T item) {
    return new Cell<>(this, item);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
