/*
 * Topiello Examples - Example Topiello grammars
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
package org.topiello.example.expression;

import java.util.Collection;
import java.util.stream.Stream;

public class Multiplication implements Expression {
  public Multiplication(Collection<? extends Expression> factors) {
    // TODO Auto-generated constructor stub
  }

  public Multiplication(Expression... factors) {
    // TODO Auto-generated constructor stub
  }

  public Stream<Expression> factors() {
    throw new UnsupportedOperationException();
  }
}