/*
 * Copyright (C) 2018 Elias N Vasylenko <eliasvasylenko@strangeskies.co.uk>
 *      __   _______  ____           _       __     _      __       __
 *    ,`_ `,|__   __||  _ `.        / \     |  \   | |  ,-`__`¬  ,-`__`¬
 *   ( (_`-'   | |   | | ) |       / . \    | . \  | | / .`  `' / .`  `'
 *    `._ `.   | |   | |<. L      / / \ \   | |\ \ | || |    _ | '--.
 *   _   `. \  | |   | |  `.`.   / /   \ \  | | \ \| || |   | || +--'
 *  \ \__.' /  | |   | |    \ \ / /     \ \ | |  \ ` | \ `._' | \ `.__,.
 *   `.__.-`   |_|   |_|    |_|/_/       \_\|_|   \__|  `-.__.J  `-.__.J
 *                   __    _         _      __      __
 *                 ,`_ `, | |  _    | |  ,-`__`¬  ,`_ `,
 *                ( (_`-' | | ) |   | | / .`  `' ( (_`-'
 *                 `._ `. | L-' L   | || '--.     `._ `.
 *                _   `. \| ,.-^.`. | || +--'    _   `. \
 *               \ \__.' /| |    \ \| | \ `.__,.\ \__.' /
 *                `.__.-` |_|    |_||_|  `-.__.J `.__.-`
 *
 * This file is part of uk.co.strangeskies.text.
 *
 * uk.co.strangeskies.text is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * uk.co.strangeskies.text is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Grammar {
  private final List<Rule> productions;
  private final Set<Pattern<Symbol>> terminals;

  protected Grammar(
      Collection<? extends Rule> rules,
      Collection<? extends Pattern<Symbol>> terminals) {
    this.productions = new ArrayList<>(rules);
    this.terminals = new LinkedHashSet<>(terminals);
  }

  public Grammar withRule(Rule rule) {
    return withRules(rule);
  }

  public Grammar withRules(Rule... rules) {
    return withProductions(Arrays.asList(rules));
  }

  public Grammar withProductions(Collection<? extends Rule> rules) {
    var productionRules = new ArrayList<Rule>(this.productions.size() + rules.size());
    productionRules.addAll(this.productions);
    rules.forEach(productionRules::add);
    return new Grammar(productionRules, terminals);
  }

  public Grammar withTerminal(Pattern<Symbol> symbol) {
    return withTerminals(symbol);
  }

  @SafeVarargs
  public final Grammar withTerminals(Pattern<Symbol>... symbols) {
    return withTerminals(Arrays.asList(symbols));
  }

  public Grammar withTerminals(Collection<? extends Pattern<Symbol>> symbols) {
    var terminals = new LinkedHashSet<Pattern<Symbol>>(this.terminals.size() + symbols.size());
    terminals.addAll(this.terminals);
    symbols.forEach(terminals::add);
    return new Grammar(productions, terminals);
  }

  public Stream<Rule> getProductions() {
    return productions.stream();
  }

  public Stream<Pattern<Symbol>> getTerminals() {
    return terminals.stream();
  }
}
