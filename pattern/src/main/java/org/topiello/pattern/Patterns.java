/*
 * Topiello Pattern - A pattern API for meta-pattern attribute grammars
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
package org.topiello.pattern;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.topiello.pattern.symbol.LexicalItem;
import org.topiello.pattern.symbol.Sequence;

/**
 * 
 * 
 * 
 * 
 * 
 * TODO two different contexts we may use patterns
 * 
 * In one, we need no type-checking and also have no good way to properly type
 * variables, this is where we're defining a rule and defining constraints.
 * 
 * In another we're resolving against instantiations and we need type checking.
 * We don't necessarily know what types are valid for a variable, but we can
 * properly statically check if it satisfies the type we give! Which means
 * symbols extracted are validated as the correct runtime type. This is useful
 * for example in the semantics API, which is the part where we interface
 * between Java and the underlying grammar model, so it makes sense to be the
 * place that the java type system is applied.
 * 
 * 
 * 
 */
public final class Patterns {
  private static final Wildcard WILDCARD = new Wildcard();
  private static final Literal NIL = new Literal(Sequence.empty());

  private Patterns() {}

  public static Pattern cons(Pattern car, Pattern cdr) {
    return new Cons(car, cdr);
  }

  public static Pattern cons(LexicalItem car, Pattern cdr) {
    return new Cons(literal(car), cdr);
  }

  public static Pattern cons(Pattern car, Sequence cdr) {
    return new Cons(car, literal(cdr));
  }

  public static Pattern sequence(Pattern... items) {
    return sequence(Arrays.asList(items));
  }

  public static Pattern sequence(Collection<? extends Pattern> items) {
    return sequence(List.copyOf(items));
  }

  private static Pattern sequence(List<Pattern> items) {
    return items.size() == 0
        ? NIL
        : new Cons(items.get(0), sequence(items.subList(1, items.size())));
  }

  public static Pattern literal(LexicalItem lexicalItem) {
    return new Literal(lexicalItem);
  }

  public static final Pattern wildcard() {
    return WILDCARD;
  }

  public static Variable variable(String name) {
    return new Variable(name);
  }
}
