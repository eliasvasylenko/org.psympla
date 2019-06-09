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

import org.topiello.earley.EarleyState;

public class InterpretedConditionalThenElse implements InterpretedExecute {
  private final InterpretedEvaluate<Boolean> condition;
  private final InterpretedExecute ifThen;
  private final InterpretedExecute elseThen;

  public InterpretedConditionalThenElse(
      InterpretedEvaluate<Boolean> condition,
      InterpretedExecute ifThen,
      InterpretedExecute elseThen) {
    this.condition = condition;
    this.ifThen = ifThen;
    this.elseThen = elseThen;
  }

  @Override
  public void execute(EarleyState<?> state) {
    if (condition.evaluate(state)) {
      ifThen.execute(state);
    } else {
      elseThen.execute(state);
    }
  }
}
