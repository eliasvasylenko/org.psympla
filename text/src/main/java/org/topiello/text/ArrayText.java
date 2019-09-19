/*
 * Topiello Text - The text API
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
package org.topiello.text;

import static java.lang.reflect.Array.newInstance;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * 
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class ArrayText<C extends TextUnit> implements Text<C> {
  private final int from;
  private final int to;
  private final C[] characters;

  @SuppressWarnings("unchecked")
  public ArrayText(Collection<? extends C> characters, Class<C> characterClass) {
    this.characters = characters.toArray(l -> (C[]) newInstance(characterClass, l));
    this.from = 0;
    this.to = characters.size();
  }

  protected ArrayText(C[] characters, int from, int to) {
    this.characters = characters;
    this.from = from;
    this.to = to;
  }

  @Override
  public Iterator<C> iterator() {
    return asList(characters).subList(from, to).iterator();
  }

  @Override
  public long length() {
    return to - from;
  }

  @Override
  public Text<C> subSequence(int from, int to) {
    if (from < 0) {
      throw new IndexOutOfBoundsException(from);
    }
    if (to > length()) {
      throw new IndexOutOfBoundsException(to);
    }

    from += this.from;
    to += this.from;

    return new ArrayText<>(characters, from, to);
  }

  @Override
  public Stream<C> stream() {
    return Stream.of(characters).limit(to).skip(from);
  }
}
