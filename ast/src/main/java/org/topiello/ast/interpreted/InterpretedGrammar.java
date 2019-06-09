/*
 * Topiello AST - The parser AST API
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
package org.topiello.ast.interpreted;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

import org.topiello.ast.GrammarNode;
import org.topiello.earley.EarleyState;
import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.grammar.Variable;
import org.topiello.parseforest.ParseNode;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class InterpretedGrammar<T extends Product, C extends TextUnit>
    implements GrammarNode<T, C> {
  private static final Variable ACCEPT_SYMBOL = new Variable() {};

  private final Set<InterpretedRule> rules = new HashSet<>();

  private final InterpretedTopiello topiello;

  public InterpretedGrammar(InterpretedTopiello topiello) {
    this.topiello = topiello;
  }

  InterpretedRule addRule(InterpretedRule rule) {
    rules.add(rule);
    return rule;
  }

  @Override
  public Set<InterpretedRule> getRules() {
    return unmodifiableSet(rules);
  }

  @Override
  public ParseNode parse(T product, Text<C> text) {
    var startRule = topiello.addAnonymousRule(this, new Rule<>(ACCEPT_SYMBOL, product));
    var state = new EarleyState<>();
    startRule.predicted().execute(state);
    throw new UnsupportedOperationException();
  }
}
