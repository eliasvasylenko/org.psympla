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
package org.topiello.ast.index;

import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.stream.Stream;

import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;

/**
 * A synthetic rule representing a lexical class. The LHS is the terminal symbol
 * which matches the lexical class, and the RHS is a variable intended to be
 * instantiated to the input text which is successfully parsed.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
// TODO value type?
public class IndexedRule<T extends Product> {
  private final int index;

  private final Rule<T> rule;

  private List<IndexedItem> items;

  IndexedRule(int index, IndexedLanguage<T, ?> indexedLanguage, Rule<T> rule) {
    this.index = index;

    this.rule = rule;
  }

  public int index() {
    return index;
  }

  public Rule<T> rule() {
    return rule;
  }

  public IndexedItem item(int index) {
    return items.get(index);
  }

  public int itemCount() {
    return items.size();
  }

  public Stream<IndexedItem> items() {
    return items.stream();
  }

  public T product(int index) {
    return rule.product(index);
  }

  public int productCount() {
    return rule.length();
  }

  public Stream<T> products() {
    return range(0, productCount()).mapToObj(rule::product);
  }
}
