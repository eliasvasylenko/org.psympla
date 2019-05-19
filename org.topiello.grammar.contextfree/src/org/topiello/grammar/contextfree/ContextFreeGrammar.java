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
package org.topiello.grammar.contextfree;

import static java.util.Collections.singleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Rule;
import org.topiello.grammar.Terminal;
import org.topiello.lexicon.Lexicon;
import org.topiello.text.TextUnit;

public class ContextFreeGrammar<C extends TextUnit> implements Grammar<Symbol, C> {
  private final List<ContextFreeRule> rules;

  protected ContextFreeGrammar(Collection<? extends ContextFreeRule> rules) {
    this(new ArrayList<>(rules));
  }

  private ContextFreeGrammar(List<ContextFreeRule> rules) {
    this.rules = rules;
  }

  public ContextFreeGrammar<C> withRule(ContextFreeRule rule) {
    return withRules(singleton(rule));
  }

  public ContextFreeGrammar<C> withRules(Collection<? extends ContextFreeRule> rules) {
    var newRules = new ArrayList<ContextFreeRule>(this.rules.size() + rules.size());
    newRules.addAll(this.rules);
    rules.forEach(newRules::add);
    return new ContextFreeGrammar<>(newRules);
  }

  public ContextFreeGrammar<C> withTerminals(Lexicon<Symbol, C> lexicon) {
    var newRules = new ArrayList<ContextFreeRule>(this.rules.size() + rules.size());
    newRules.addAll(this.rules);
    rules.forEach(newRules::add);
    return new ContextFreeGrammar<>(newRules);
  }

  @Override
  public Stream<ContextFreeRule> getRules() {
    return rules.stream();
  }

  @Override
  public Stream<? extends Rule<Symbol>> getMatchingRules(Symbol product) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<? extends Terminal<C>> getTerminals() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<? extends Terminal<C>> getMatchingTerminals(Symbol product) {
    // TODO Auto-generated method stub
    return null;
  }
}
