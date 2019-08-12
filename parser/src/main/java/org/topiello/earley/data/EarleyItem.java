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
package org.topiello.earley.data;

import java.util.List;

import org.topiello.ast.ItemNode;
import org.topiello.parseforest.LR0Item;
import org.topiello.parseforest.ParseNode;

public final class EarleyItem {
  private static final int INITIAL_PREDICTOR_LIST_SIZE = 8;

  private final ItemNode lr0Item;

  private final ParseNode parseNode;
  private final List<EarleyItem> predictors;

  EarleyItem(ItemNode lr0Item, int from, int to) {
    this.lr0Item = lr0Item;

    this.parseNode = new ParseNode(lr0Item, from, to);
    this.predictors = null;
  }

  EarleyItem(ItemNode lr0Item, EarleyItem advancedFrom, EarleyItem advancedOver) {
    this.lr0Item = lr0Item;
    
    this.parseNode = new ParseNode(LR0Item, advancedFrom., rightExtent)
    this.predictors = advancedFrom.predictors;
  }

  public ItemNode lr0Item() {
    return lr0Item;
  }
}
