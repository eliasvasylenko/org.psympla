/*
 * Topiello Semantics - Semantic interpretation of Topiello parse trees
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
package org.topiello.semantics;

import java.util.stream.Stream;

import org.topiello.language.Sign;

public interface DecodeState {
  <T> T get(Sign<T> signifier);

  <T> T get(Sign<T> signifier, int index);

  <T> Stream<T> getRemaining(Sign<T> signifier);

  <T> Stream<T> getAll(Sign<T> signifier);

  <U> U getContext(Class<U> type);

  String getTerminal();
}
