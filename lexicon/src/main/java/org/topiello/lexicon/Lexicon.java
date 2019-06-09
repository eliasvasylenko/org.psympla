/*
 * Topiello Lexicon - An API for creating simple lexicons to be consumed by grammars
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
package org.topiello.lexicon;

import java.util.stream.Stream;

import org.topiello.text.TextUnit;

/**
 * A lexicon defines which tokens exist over a given character set.
 * <p>
 * Given a terminal symbol and a sequence of characters, it can determine which
 * tokens of that symbol can be recognized.
 * <p>
 * The tokens recognized may be parameterized.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Lexicon<T extends Token<?, ?>, C extends TextUnit> {
  /*
   * TODO what API do we REALLY want here?
   * 
   * Lexicon implementation should be in charge of actual lexing algorithm, not
   * just be a dumb holder for lexical classes. We tell it possible expected token
   * types and an input position, it returns possible matching tokens.
   * 
   * Should this be totally general? Capture all use-cases? What about cases where
   * local lexing isn't important, i.e. always only a single candidate scan, and
   * always resume scanning with input position at the end of the last scan...
   * 
   * How does it relate to the Terminal type defined by a grammar?
   * 
   * Say we have multiple lexicons for a particular grammar, the indices of the
   * predicted Terminals don't line up with the indices of the lexical classes of
   * each lexicon, so how do we translate the bitset into something we can consume
   * here? We could take a slice of the bitset? That breaks down when different
   * sources of Terminals produce specializable Terminals, so lexicons must not be
   * allowed to be speciailzable. Seems reasonable enough.
   * 
   * Maybe we don't need a bitset as input here and can just pass in an actual set
   * of LexicalClasses? And a Lexicon doesn't need to be indexed? The bitset is to
   * fast-track tests of whether we've already parsed something, which we can do
   * against terminals without needing to pass down to the lexicon...
   * 
   * Tension, local lexing allows for certain optimisations, traditional lexing
   * allows for certain other optimisations (e.g. perfect hash of first few
   * characters to detect which potential keyword to check against), it's
   * difficult to marry the two.
   */

  ;

  Stream<? extends LexicalClass<? extends T, C>> getLexicalClasses();

  Stream<? extends LexicalClass<? extends T, C>> getMatchingLexicalClasses(T token);
}
