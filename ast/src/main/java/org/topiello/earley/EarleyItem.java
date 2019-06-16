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
package org.topiello.earley;

import java.util.List;

import org.topiello.ast.ItemNode;
import org.topiello.parseforest.ParseNode;

public class EarleyItem {
  private static final int INITIAL_PREDICTOR_LIST_SIZE = 8;

  private final ParseNode parseNode = null;

  private final ItemNode lr0Item;

  private final EarleyItem advancedFrom;
  private final List<EarleyItem> predictors; // if we completed from an earlier item, we share
                                             // this

  EarleyItem(ItemNode lr0Item) {
    this.lr0Item = lr0Item;
    this.advancedFrom = null;
    this.predictors = null;
  }

  EarleyItem(ItemNode lr0Item, EarleyItem advancedFrom) {
    this.lr0Item = lr0Item;
    this.advancedFrom = advancedFrom;
    this.predictors = advancedFrom.predictors;
  }

  EarleyItem advance(EarleyItem completed) {
    return new EarleyItem(((ItemNode.Specialized) lr0Item).nextItem().get(), this);
  }

  public ItemNode lr0Item() {
    return lr0Item;
  }
}
