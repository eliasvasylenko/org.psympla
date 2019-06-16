/*
 * Topiello Pattern Grammar - A grammar implementation for meta-pattern attribute grammars
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.topiello.pattern.grammar;

import java.util.Optional;

import org.topiello.pattern.Cons;
import org.topiello.pattern.Literal;
import org.topiello.pattern.Pattern;
import org.topiello.pattern.symbol.Cell;
import org.topiello.pattern.symbol.LexicalItem;
import org.topiello.pattern.symbol.Symbol;

// TODO value class
class RulePattern {
  private final boolean parametric;
  private final Optional<Symbol> symbol;

  public RulePattern(Pattern pattern) {
    LexicalItem identifier;

    if (pattern.getClass() == Literal.class) {
      var lexicalItem = ((Literal) pattern).lexicalItem();

      this.parametric = lexicalItem.getClass() == Cell.class;
      identifier = parametric ? ((Cell<?, ?>) lexicalItem).car() : lexicalItem;

    } else {
      Pattern identifierPattern;

      this.parametric = pattern.getClass() == Cons.class;
      identifierPattern = parametric ? ((Cons) pattern).car() : pattern;
      identifier = identifierPattern instanceof Literal
          ? ((Literal) identifierPattern).lexicalItem()
          : null;
    }

    this.symbol = identifier instanceof Symbol
        ? Optional.of((Symbol) identifier)
        : Optional.empty();
  }

  public boolean parametric() {
    return parametric;
  }

  public Optional<Symbol> symbol() {
    return symbol;
  }
}
