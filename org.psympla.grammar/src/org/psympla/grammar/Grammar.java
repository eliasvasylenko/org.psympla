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

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.psympla.symbol.Symbol;

public class Grammar {
  private static final Grammar EMPTY = new Grammar(emptyList(), emptySet());

  public static final Grammar empty() {
    return EMPTY;
  }

  private final List<Rule> rules;
  private final Set<Symbol> terminals;

  protected Grammar(Collection<? extends Rule> rules, Collection<? extends Symbol> terminals) {
    this(new ArrayList<>(rules), new LinkedHashSet<>(terminals));
  }

  private Grammar(List<Rule> rules, Set<Symbol> terminals) {
    this.rules = rules;
    this.terminals = terminals;
  }

  public Grammar withRule(Rule rule) {
    return withRules(singleton(rule));
  }

  public Grammar withRules(Collection<? extends Rule> rules) {
    var newRules = new ArrayList<Rule>(this.rules.size() + rules.size());
    newRules.addAll(this.rules);
    rules.forEach(newRules::add);
    return new Grammar(newRules, terminals);
  }

  public Grammar withTerminal(Symbol terminal) {
    return withTerminals(singleton(terminal));
  }

  public Grammar withTerminals(Collection<? extends Symbol> terminals) {
    var newTerminals = new LinkedHashSet<Symbol>(this.terminals.size() + terminals.size());
    newTerminals.addAll(this.terminals);
    terminals.forEach(newTerminals::add);
    return new Grammar(rules, newTerminals);
  }

  public Stream<Rule> getRules() {
    return rules.stream();
  }

  public Stream<Symbol> getTerminals() {
    return terminals.stream();
  }
}
