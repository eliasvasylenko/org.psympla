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
  private final List<Rule> rules;
  private final Set<Pattern<Symbol>> terminals;

  protected Grammar(
      Collection<? extends Rule> rules,
      Collection<? extends Pattern<Symbol>> terminals) {
    this(new ArrayList<>(rules), new LinkedHashSet<>(terminals));
  }

  private Grammar(List<Rule> rules, LinkedHashSet<Pattern<Symbol>> terminals) {
    this.rules = rules;
    this.terminals = terminals;
  }

  public Grammar withRule(Rule rule) {
    return withRules(rule);
  }

  public Grammar withRules(Rule... rules) {
    return withProductions(Arrays.asList(rules));
  }

  public Grammar withProductions(Collection<? extends Rule> rules) {
    var newRules = new ArrayList<Rule>(this.rules.size() + rules.size());
    newRules.addAll(this.rules);
    rules.forEach(newRules::add);
    return new Grammar(newRules, terminals);
  }

  public Grammar withTerminal(Pattern<Symbol> terminals) {
    return withTerminals(terminals);
  }

  @SafeVarargs
  public final Grammar withTerminals(Pattern<Symbol>... terminals) {
    return withTerminals(Arrays.asList(terminals));
  }

  public Grammar withTerminals(Collection<? extends Pattern<Symbol>> terminals) {
    var newTerminals = new LinkedHashSet<Pattern<Symbol>>(this.terminals.size() + terminals.size());
    newTerminals.addAll(this.terminals);
    terminals.forEach(newTerminals::add);
    return new Grammar(rules, newTerminals);
  }

  public Stream<Rule> getProductions() {
    return rules.stream();
  }

  public Stream<Pattern<Symbol>> getTerminals() {
    return terminals.stream();
  }
}
