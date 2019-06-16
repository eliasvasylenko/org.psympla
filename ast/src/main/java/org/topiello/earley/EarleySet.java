/*
 * Topiello AST - The parser AST API
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
package org.topiello.earley;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.topiello.ast.ItemNode;

public class EarleySet {
  private final int inputPosition;

  /*
   * Closure of null-predicted items, i.e. items with start position at the
   * current input position.
   */
  private final BitSet predictedNonterminals;
  private final BitSet predictedTerminals;

  /*
   * Items from scanning or completion, with start position earlier to the current
   * input position.
   */
  private final Map<ItemNode, EarleyItem> items;

  public EarleySet(int inputPosition) {
    this.inputPosition = inputPosition;
    this.items = new HashMap<>();

    this.predictedNonterminals = new BitSet();
    this.predictedTerminals = new BitSet();
  }

  public int inputPosition() {
    return inputPosition;
  }

  EarleyItem getItem(ItemNode lr0Item) {
    return items.computeIfAbsent(lr0Item, EarleyItem::new);
  }

  public Stream<EarleyItem> nodes() {
    return items.values().stream();
  }
}
