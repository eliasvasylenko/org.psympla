/*
 * Topiello Pattern Constraint - Constraints for patterns
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
package org.topiello.pattern.constraint;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import org.topiello.pattern.Variable;
import org.topiello.pattern.symbol.LexicalItem;

public class Or implements Constraint {
  private final Set<Constraint> constraints;

  public Or(Constraint... constraints) {
    this.constraints = Set.of(constraints);
  }

  public Or(Collection<? extends Constraint> constraints) {
    this.constraints = Set.copyOf(constraints);
  }

  @Override
  public Stream<Variable> variables() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Constraint withInstantiation(Variable variable, LexicalItem lexicalItem) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<Constructor<?>> constructors(Variable variable) {
    // TODO Auto-generated method stub
    return null;
  }
}
