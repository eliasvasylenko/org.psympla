/*
 * Topiello Context-Free Grammar - A basic context-free grammar implementation
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
package org.topiello.grammar.contextfree;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public abstract class ContextFreeLexicalClass<T, C extends TextUnit>
    implements LexicalClass<Token<Symbol, T>, C> {
  private final Symbol variable;

  public ContextFreeLexicalClass(CharacterSet<C> characterSet, Symbol variable) {
    this.variable = variable;
  }

  public Symbol variable() {
    return variable;
  }

  @Override
  public String toString() {
    return variable.toString();
  }

  public static class Regex<C extends TextUnit> extends ContextFreeLexicalClass<String, C> {
    public Regex(CharacterSet<C> characterSet, Symbol variable, String regex) {
      super(characterSet, variable);
    }

    @Override
    public Stream<Lexeme<Token<Symbol, String>, C>> scan(Text<C> characters) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Optional<Lexeme<Token<Symbol, String>, C>> print(Token<Symbol, String> token) {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public static class Literal<C extends TextUnit> extends ContextFreeLexicalClass<Void, C> {
    public Literal(CharacterSet<C> characterSet, Symbol variable, String regex) {
      super(characterSet, variable);
    }

    @Override
    public Stream<Lexeme<Token<Symbol, Void>, C>> scan(Text<C> characters) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Optional<Lexeme<Token<Symbol, Void>, C>> print(Token<Symbol, Void> token) {
      // TODO Auto-generated method stub
      return null;
    }
  }
}
