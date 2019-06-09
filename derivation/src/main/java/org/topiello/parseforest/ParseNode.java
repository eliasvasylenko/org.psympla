/*
 * Topiello Derivation - API for describing parse forests, deparse forests, and derivation trees
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
package org.topiello.parseforest;

import java.util.TreeMap;

public class ParseNode {
  private final LR0Item item;
  private final int leftExtent;
  private final int rightExtent;
  private final TreeMap<Integer, PivotNode> pivots;

  public ParseNode(LR0Item item, int leftExtent, int rightExtent) {
    this.item = item;
    this.leftExtent = leftExtent;
    this.rightExtent = rightExtent;
    this.pivots = new TreeMap<>();
  }

  public LR0Item item() {
    return item;
  }

  public int leftExtent() {
    return leftExtent;
  }

  public int rightExtent() {
    return rightExtent;
  }

  public PivotNode getPivot(int position) {
    return pivots
        .computeIfAbsent(
            position,
            p -> new PivotNode(
                p,
                item.dotPosition() > 1
                    ? new ParseNode(
                        new LR0Item(item.rule(), item.dotPosition() - 1),
                        leftExtent,
                        position)
                    : null));
  }
}
