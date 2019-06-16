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

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Lexicon;
import org.topiello.lexicon.Token;
import org.topiello.text.TextUnit;

public class ContextFreeLexicon<C extends TextUnit> implements Lexicon<Token<Symbol, ?>, C> {
  private final Map<Symbol, ContextFreeLexicalClass<?, C>> lexicalClasses;

  @SafeVarargs
  public ContextFreeLexicon(ContextFreeLexicalClass<?, C>... lexicalClasses) {
    this(Arrays.asList(lexicalClasses));
  }

  public ContextFreeLexicon(Collection<? extends ContextFreeLexicalClass<?, C>> lexicalClasses) {
    this.lexicalClasses = lexicalClasses
        .stream()
        .collect(toMap(ContextFreeLexicalClass::variable, Function.identity()));
  }

  @Override
  public Stream<? extends ContextFreeLexicalClass<?, C>> getLexicalClasses() {
    return lexicalClasses.values().stream();
  }

  public Optional<? extends ContextFreeLexicalClass<?, C>> getLexicalClass(Symbol variable) {
    return Optional.ofNullable(lexicalClasses.get(variable));
  }

  @Override
  public Stream<? extends LexicalClass<? extends Token<Symbol, ?>, C>> getMatchingLexicalClasses(
      Token<Symbol, ?> token) {
    // TODO Auto-generated method stub
    return null;
  }
}
