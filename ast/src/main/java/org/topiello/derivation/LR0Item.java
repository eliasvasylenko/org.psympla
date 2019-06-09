/*
 * Topiello AST - The parser AST API
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
package org.topiello.derivation;

import org.topiello.grammar.Rule;

// TODO value & record type
public class LR0Item {
  private final Rule<?> rule;
  private final int dotPosition;

  public LR0Item(Rule<?> rule, int dotPosition) {
    this.rule = rule;
    this.dotPosition = dotPosition;
  }

  public Rule<?> rule() {
    return rule;
  }

  public int dotPosition() {
    return dotPosition;
  }
}
