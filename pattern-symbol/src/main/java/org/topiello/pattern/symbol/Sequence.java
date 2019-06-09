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

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public interface Sequence extends LexicalItem {
  Stream<LexicalItem> elements();

  public static Nil empty() {
    return Nil.INSTANCE;
  }

  public static <T extends LexicalItem> Cell<T, Nil> of(T item) {
    return new Cell<>(item, empty());
  }

  public static <T extends LexicalItem, U extends LexicalItem> Cell<T, Cell<U, Nil>> of(
      T item1,
      U item2) {
    return new Cell<>(item1, of(item2));
  }

  public static <T extends LexicalItem, U extends LexicalItem, V extends LexicalItem> Cell<T, Cell<U, Cell<V, Nil>>> of(
      T item1,
      U item2,
      V item3) {
    return new Cell<>(item1, of(item2, item3));
  }

  public static <T extends LexicalItem, U extends LexicalItem, V extends LexicalItem, W extends LexicalItem> Cell<T, Cell<U, Cell<V, Cell<W, Nil>>>> of(
      T item1,
      U item2,
      V item3,
      W item4) {
    return new Cell<>(item1, of(item2, item3, item4));
  }

  public static Sequence of(LexicalItem... items) {
    return of(asList(items));
  }

  public static Sequence of(Collection<? extends LexicalItem> items) {
    return of(List.copyOf(items));
  }

  public static Sequence of(List<? extends LexicalItem> items) {
    return items.size() == 0 ? empty() : items.get(0).consOnto(of(items.subList(0, items.size())));
  }
}
