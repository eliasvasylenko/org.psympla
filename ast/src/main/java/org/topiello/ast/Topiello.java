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
package org.topiello.ast;

import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.text.TextUnit;

public interface Topiello {
  <T extends Product, U extends TextUnit> GrammarNode<T, U> createGrammar();

  RuleNode addRule(GrammarNode<?, ?> grammar, Rule<?> rule);

  RuleNode addAnonymousRule(GrammarNode<?, ?> grammar, Rule<?> rule);

  EvaluableNode<Boolean> invert(EvaluableNode<Boolean> node);

  ExecutableNode createConditional(EvaluableNode<Boolean> condition, ExecutableNode ifThen);

  ExecutableNode createConditional(
      EvaluableNode<Boolean> condition,
      ExecutableNode ifThen,
      ExecutableNode elseThen);
}
