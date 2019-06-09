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
package org.topiello.lexicon.scanning;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class ScanningLexeme<T extends Token<?, ?>, C extends TextUnit> implements Lexeme<T, C> {
  private final ScanningLexicalClass<T, C> lexicalClass;
  private final Text<C> characters;
  private final Scan<T> scan;
  private T token;

  protected ScanningLexeme(
      ScanningLexicalClass<T, C> lexicalClass,
      Text<C> characters,
      Scan<T> scan) {
    this.lexicalClass = lexicalClass;
    this.characters = characters.subSequence(0, scan.length());
    this.scan = scan;
  }

  @Override
  public LexicalClass<T, C> lexicalClass() {
    return lexicalClass;
  }

  @Override
  public Text<C> characters() {
    return characters;
  }

  @Override
  public T evaluate() {
    if (token == null) {
      token = scan.evaluate();
    }
    return token;
  }
}
