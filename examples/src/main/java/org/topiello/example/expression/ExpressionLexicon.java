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

import static org.topiello.text.utf.UtfCodePoint.CODE_POINTS;

import org.topiello.grammar.contextfree.ContextFreeLexicalClass;
import org.topiello.grammar.contextfree.ContextFreeLexicon;
import org.topiello.grammar.contextfree.Symbol;
import org.topiello.text.utf.UtfCodePoint;

public class ExpressionLexicon extends ContextFreeLexicon<UtfCodePoint> {
  public static class OperatorSymbol extends Symbol {
    public OperatorSymbol(String id) {
      super(id);
    }
  }

  public static final OperatorSymbol MULTIPLY = new OperatorSymbol("multiply");
  public static final OperatorSymbol DIVIDE = new OperatorSymbol("divide");
  public static final OperatorSymbol ADD = new OperatorSymbol("add");
  public static final OperatorSymbol SUBTRACT = new OperatorSymbol("subtract");
  public static final OperatorSymbol OPEN_BRACKET = new OperatorSymbol("open-bracket");
  public static final OperatorSymbol CLOSE_BRACKET = new OperatorSymbol("close-bracket");
  public static final Symbol VARIABLE = new Symbol("variable");

  public ExpressionLexicon() {
    super(
        new ContextFreeLexicalClass.Regex<>(CODE_POINTS, VARIABLE, "[A-Za-z]"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, MULTIPLY, "\\*"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, DIVIDE, "/"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, ADD, "+"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, SUBTRACT, "-"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, OPEN_BRACKET, "("),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, CLOSE_BRACKET, ")"));
  }

  @SuppressWarnings("unchecked")
  public ContextFreeLexicalClass.Regex<UtfCodePoint> variable() {
    return (ContextFreeLexicalClass.Regex<UtfCodePoint>) getLexicalClass(VARIABLE).get();
  }

  @SuppressWarnings("unchecked")
  public ContextFreeLexicalClass.Literal<UtfCodePoint> operator(OperatorSymbol symbol) {
    return (ContextFreeLexicalClass.Literal<UtfCodePoint>) getLexicalClass(symbol).get();
  }
}
