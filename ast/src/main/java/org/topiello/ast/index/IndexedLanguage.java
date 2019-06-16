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

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.stream.Stream;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Product;
import org.topiello.text.TextUnit;

public class IndexedLanguage<T extends Product, C extends TextUnit> {
  private final List<IndexedRule<T>> indexedRules;
  private final List<IndexedTerminal<C>> indexedTerminals;

  public IndexedLanguage(Grammar<T, C> grammar) {
    var rules = grammar.getRules().collect(toList());
    this.indexedRules = range(0, rules.size())
        .mapToObj(i -> new IndexedRule<>(i, this, rules.get(i)))
        .collect(toList());

    var terminals = grammar.getTerminals().collect(toList());
    this.indexedTerminals = range(0, terminals.size())
        .mapToObj(i -> new IndexedTerminal<>(i, this, terminals.get(i)))
        .collect(toList());

    rules().flatMap(IndexedRule::items).forEach(item -> {
      System.out.println(item.item());
      System.out.println();
    });
  }

  public IndexedRule<T> rule(int index) {
    return indexedRules.get(index);
  }

  public int ruleCount() {
    return indexedRules.size();
  }

  public Stream<IndexedRule<T>> rules() {
    return indexedRules.stream();
  }

  public IndexedTerminal<C> terminal(int index) {
    return indexedTerminals.get(index);
  }

  public int terminalCount() {
    return indexedTerminals.size();
  }

  public Stream<IndexedTerminal<C>> terminals() {
    return indexedTerminals.stream();
  }
}
