/*
 * Topiello Examples - Example Topiello grammars
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
package org.topiello.example.expression;

import static java.util.Arrays.asList;
import static org.topiello.example.expression.ExpressionLexicon.ADD;
import static org.topiello.example.expression.ExpressionLexicon.CLOSE_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.DIVIDE;
import static org.topiello.example.expression.ExpressionLexicon.MULTIPLY;
import static org.topiello.example.expression.ExpressionLexicon.OPEN_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.SUBTRACT;
import static org.topiello.example.expression.ExpressionLexicon.VARIABLE;

import org.topiello.grammar.Rule;
import org.topiello.grammar.contextfree.ContextFreeGrammar;
import org.topiello.grammar.contextfree.Symbol;
import org.topiello.text.utf.UtfCodePoint;

public class ExpressionGrammar extends ContextFreeGrammar<UtfCodePoint> {
  public static final Symbol BINARY_OPERATOR = new Symbol("binary-operator");
  public static final Symbol EXPRESSION = new Symbol("expression");

  private static final ExpressionGrammar INSTANCE = new ExpressionGrammar();

  public static ExpressionGrammar instance() {
    return INSTANCE;
  }

  private ExpressionGrammar() {
    super(
        new ExpressionLexicon(),
        asList(

            new Rule<>(EXPRESSION, VARIABLE),

            new Rule<>(EXPRESSION, EXPRESSION, MULTIPLY, EXPRESSION),
            new Rule<>(EXPRESSION, EXPRESSION, DIVIDE, EXPRESSION),
            new Rule<>(EXPRESSION, EXPRESSION, ADD, EXPRESSION),
            new Rule<>(EXPRESSION, EXPRESSION, SUBTRACT, EXPRESSION),

            new Rule<>(EXPRESSION, OPEN_BRACKET, EXPRESSION, CLOSE_BRACKET)

        ));
  }
}
