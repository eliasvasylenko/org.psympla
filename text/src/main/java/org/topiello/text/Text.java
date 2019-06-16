/*
 * Topiello Text - The text API
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.topiello.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Text<C extends TextUnit> extends Iterable<C> {
  static <C extends TextUnit> Text<C> fromStream(Stream<C> stream) {
    return new Text<C>() {
      @Override
      public Text<C> subSequence(int from, int to) {
        return null; // TODO
      }

      @Override
      public Iterator<C> iterator() {
        return new Iterator<C>() {
          @Override
          public boolean hasNext() {
            return false;
          }

          @Override
          public C next() {
            throw new NoSuchElementException();
          }
        };
      }

      @Override
      public Stream<C> stream() {
        return Stream.empty();
      }
    };
  }

  Text<C> subSequence(int from, int to);

  default Text<C> subSequence(int to) {
    return subSequence(0, to);
  }

  static <C extends TextUnit> Text<C> empty() {
    return new Text<C>() {
      @Override
      public Text<C> subSequence(int from, int to) {
        if (from != 0 || to != 0)
          throw new IndexOutOfBoundsException();
        return this;
      }

      @Override
      public Iterator<C> iterator() {
        return new Iterator<C>() {
          @Override
          public boolean hasNext() {
            return false;
          }

          @Override
          public C next() {
            throw new NoSuchElementException();
          }
        };
      }

      @Override
      public Stream<C> stream() {
        return Stream.empty();
      }
    };
  }

  default boolean startsWith(Text<C> that) {
    var thisIterator = this.iterator();
    var thatIterator = that.iterator();

    while (thatIterator.hasNext()) {
      if (!thisIterator.hasNext()) {
        return false;
      }

      if (!Objects.equals(thisIterator.next(), thatIterator.next())) {
        return false;
      }
    }

    return true;
  }

  Stream<C> stream();
}
