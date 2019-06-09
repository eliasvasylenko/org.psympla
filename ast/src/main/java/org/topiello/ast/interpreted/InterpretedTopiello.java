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

import org.topiello.ast.EvaluableNode;
import org.topiello.ast.ExecutableNode;
import org.topiello.ast.GrammarNode;
import org.topiello.ast.Topiello;
import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.text.TextUnit;

public class InterpretedTopiello implements Topiello {
  @Override
  public <T extends Product, U extends TextUnit> InterpretedGrammar<T, U> createGrammar() {
    return new InterpretedGrammar<>(this);
  }

  @Override
  public InterpretedRule addRule(GrammarNode<?, ?> grammar, Rule<?> rule) {
    return ((InterpretedGrammar<?, ?>) grammar).addRule(new InterpretedRule(rule));
  }

  @Override
  public InterpretedEvaluate<Boolean> invert(EvaluableNode<Boolean> node) {
    return new InterpretedInvert((InterpretedEvaluate<Boolean>) node);
  }

  @Override
  public InterpretedExecute createConditional(
      EvaluableNode<Boolean> condition,
      ExecutableNode ifThen) {
    return new InterpretedConditionalThen(
        (InterpretedEvaluate<Boolean>) condition,
        (InterpretedExecute) ifThen);
  }

  @Override
  public InterpretedExecute createConditional(
      EvaluableNode<Boolean> condition,
      ExecutableNode ifThen,
      ExecutableNode elseThen) {
    return new InterpretedConditionalThenElse(
        (InterpretedEvaluate<Boolean>) condition,
        (InterpretedExecute) ifThen,
        (InterpretedExecute) elseThen);
  }

  @Override
  public InterpretedRule addAnonymousRule(GrammarNode<?, ?> grammar, Rule<?> rule) {
    // TODO Auto-generated method stub
    return null;
  }
}
