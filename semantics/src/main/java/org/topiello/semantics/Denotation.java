/*
 * Topiello Semantics - Semantic interpretation of Topiello parse trees
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
package org.topiello.semantics;

import org.topiello.grammar.Product;
import org.topiello.language.Sign;

/**
 * A denotation specifies the relationship between a signifier and the
 * information it represents.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 */
public class Denotation<T, U extends Product> {
  private final Sign<? super T> sign;
  private final Class<T> type;
  private final Encoder<T> encoder;
  private final Decoder<T> decoder;

  public Denotation(
      Sign<? super T> sign,
      U product,
      Class<T> type,
      Encoder<T> encoder,
      Decoder<T> decoder) {
    this.sign = sign;
    this.type = type;
    this.encoder = encoder;
    this.decoder = decoder;
  }

  public Sign<? super T> sign() {
    return sign;
  }

  public Class<T> type() {
    return type;
  }

  public Encoder<T> encoder() {
    return encoder;
  }

  public Decoder<T> decoder() {
    return decoder;
  }
}
