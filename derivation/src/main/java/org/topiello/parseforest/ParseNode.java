/*
 * Topiello Derivation - API for describing parse forests, deparse forests, and derivation trees
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
package org.topiello.parseforest;

import java.util.Map;
import java.util.TreeMap;

import org.topiello.derivationtree.Extent;

public class ParseNode {
  private final Extent extent;
  private final int dotPosition;
  private final Map<Integer, ParsePivot> pivots;

  public ParseNode(Extent extent, int dotPosition) {
    this.extent = extent;
    this.dotPosition = dotPosition;
    this.pivots = new TreeMap<>();
  }

  public Extent getExtent() {
    return extent;
  }

  public int getDotPosition() {
    return dotPosition;
  }

  public ParsePivot getPivot(int position) {
    var extent = new Extent(position, getExtent().right());
    var intermediateNode = new ParseNode(extent, dotPosition + 1);
    return pivots.computeIfAbsent(position, p -> new ParsePivot(p, intermediateNode));
  }
}
