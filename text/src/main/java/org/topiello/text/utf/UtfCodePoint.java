/*
 * Topiello Text - The text API
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
package org.topiello.text.utf;

import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class UtfCodePoint implements TextUnit {
  public static final CharacterSet<UtfCodePoint> CODE_POINTS = new CharacterSet<UtfCodePoint>(
      UtfCodePoint::toChars,
      UtfCodePoint::fromChars);

  private static CharSequence toChars(Text<UtfCodePoint> text) {
    var codePoints = text.stream().mapToInt(c -> c.codePoint()).toArray();
    return new String(codePoints, 0, codePoints.length);
  }

  private static Text<UtfCodePoint> fromChars(CharSequence string) {
    return Text.fromStream(string.codePoints().mapToObj(UtfCodePoint::new));
  }

  private final int codePoint;

  public UtfCodePoint(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
