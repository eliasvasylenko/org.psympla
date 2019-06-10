/*
 * Topiello Grammar - The grammar API
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
package org.topiello.grammar;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 *          the type attached to the node that appears in the parse tree for
 *          this rule.
 */
public class Rule<T extends Product> {
  private final Variable variable;
  private final List<T> products;

  @SafeVarargs
  public Rule(Variable variable, T... products) {
    this.variable = variable;
    this.products = Arrays.asList(products);
  }

  public Rule(Variable variable, Collection<? extends T> products) {
    this.variable = variable;
    this.products = List.copyOf(products);
  }

  public Variable variable() {
    return variable;
  }

  public int length() {
    return products.size();
  }

  public T product(int index) {
    return products.get(index);
  }
}