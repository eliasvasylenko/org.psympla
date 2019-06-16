/*
 * Topiello AST - The parser AST API
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
package org.topiello.ast.index;

import java.util.BitSet;
import java.util.Set;

import org.topiello.ast.ItemNode;

public class IndexedItem {
  private final ItemNode item;

  private boolean nullScanned;

  private boolean nullCompletable;
  private final Set<ItemNode> nullReachableItems;
  private final BitSet nullReachableTerminals;

  private IndexedItem(
      ItemNode item,
      boolean nullCompletable,
      Set<ItemNode> nullReachableItems,
      BitSet nullReachableTerminals) {
    this.item = item;

    this.nullCompletable = nullCompletable;
    this.nullReachableItems = nullReachableItems;
    this.nullReachableTerminals = nullReachableTerminals;

    this.nullScanned = false;
  }

  public ItemNode item() {
    return item;
  }

  public Set<ItemNode> nullReachableItems() {
    return nullReachableItems;
  }

  public BitSet nullScannableLexicalClasses() {
    return nullReachableTerminals;
  }

  /**
   * @return true if the predecessor of the item is
   *         {@link Nullability#NULL_SCANNABLE null scannable}.
   */
  public boolean isNullScanned() {
    return nullScanned;
  }

  public boolean isNullCompletable() {
    return nullCompletable;
  }
}
