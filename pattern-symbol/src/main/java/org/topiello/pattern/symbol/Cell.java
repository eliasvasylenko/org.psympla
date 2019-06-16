/*
 * Topiello Pattern Symbol - Symbols for pattern matching grammars to match over
 *
 * Copyright © 2018 Strange Skies (elias@vasylenko.uk)
 *     __   _______  ____           _       __     _      __       __
 *   ,`_ `,|__   __||  _ `.        / \     |  \   | |  ,-`__`¬  ,-`__`¬
 *  ( (_`-'   | |   | | ) |       / . \    | . \  | | / .`  `' / .`  `'
 *   `._ `.   | |   | |-. L      / / \ \   | |\ \ | || |    _ | '-~.
 *  _   `. \  | |   | |  `.`.   / /   \ \  | | \ \| || |   | || +~-'
 * \ \__.' /  | |   | |    \ \ / /     \ \ | |  \ ` | \ `._' | \ `.__,.
 *  `.__.-`   |_|   |_|    |_|/_/       \_\|_|   \__|  `-.__.J  `-.__.J
 *                  __    _         _      __      __
 *                ,`_ `, | |  _    | |  ,-`__`¬  ,`_ `,
 *               ( (_`-' | | ) |   | | / .`  `' ( (_`-'
 *                `._ `. | L-' L   | || '-~.     `._ `.
 *               _   `. \| ,.-^.`. | || +~-'    _   `. \
 *              \ \__.' /| |    \ \| | \ `.__,.\ \__.' /
 *               `.__.-` |_|    |_||_|  `-.__.J `.__.-`
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.topiello.pattern.symbol;

import java.util.Objects;
import java.util.stream.Stream;

public class Cell<T extends LexicalItem, U extends Sequence> implements Sequence {
  private final T car;
  private final U cdr;

  protected Cell(T car, U cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public T car() {
    return car;
  }

  public U cdr() {
    return cdr;
  }

  @Override
  public Stream<LexicalItem> elements() {
    return cdr() instanceof Cell
        ? Stream.concat(Stream.of(car()), ((Cell<?, ?>) cdr()).elements())
        : Stream.of(car());
  }

  @Override
  public <V extends Sequence> Cell<Cell<T, U>, V> consOnto(V item) {
    return new Cell<>(this, item);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + car + ", " + cdr + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof Cell<?, ?>))
      return false;

    Cell<?, ?> that = (Cell<?, ?>) obj;

    return Objects.equals(this.car, that.car) && Objects.equals(this.cdr, that.cdr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(car, cdr);
  }
}
