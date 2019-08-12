/*
 * Topiello Grammar - The grammar API
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
package org.topiello.grammar;

import org.topiello.ast.AstFactory;

/* TODO
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
public class Grammar<T, V extends Variable<T>, P extends Product> {
  private final GrammarClass<T, V, P> grammarClass;
  private final AstFactory astFactory;

  public Grammar(GrammarClass<T, V, P> grammarClass, AstFactory astFactory) {
    this.grammarClass = grammarClass;
    this.astFactory = astFactory;
  }

  public GrammarClass<T, V, P> getGrammarClass() {
    return grammarClass;
  }

  public AstFactory getAstFactory() {
    return astFactory;
  }

  public Rule addRule(V variable, P... products) {

  }

  public Rule addTerminal(V variable) {

  }
}
