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
package org.topiello.grammar;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Constraints on solution to build grammars:
 * 
 * - Products must only relate to a single grammar.
 * 
 * - Grammar must be immutable.
 * 
 * - To resolve products against grammar, we may want to build an index for the
 * grammar. This index should only be built once when the grammar is done.
 * 
 * - Grammars can depend on other grammars
 * 
 * - Dependencies between grammars cannot be circular.
 * 
 * Have a grammar builder? Or have a grammar be a builder for an AST? Grammar
 * subtype controls types of rules which can be added, need special products to
 * call out to other existing grammars.
 * 
 * @author Elias N Vasylenko
 */
public class Grammar<T extends Rule<?>> {
  private final Set<Rule<?>> rules;

  private Grammar(Set<Rule<?>> rules) {
    this.rules = rules;
  }

  public Grammar(Collection<? extends Rule<?>> rules) {
    this.rules = Set.copyOf(rules);
  }

  public Grammar(Rule<?>... rules) {
    this(asList(rules));
  }

  public Stream<T> getRules() {
    return rules.stream();
  }

  public Grammar withRule(Rule<?> rule) {
    var rules = new HashSet<>(this.rules);
    rules.add(rule);
    return new Grammar(rules);
  }
}
