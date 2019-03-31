package org.psympla.text.utf;

import org.psympla.text.CharacterSet;
import org.psympla.text.TextUnit;

public class UtfCodePoint implements TextUnit {
  public static final CharacterSet<UtfCodePoint> CODE_POINTS = new CharacterSet<>();

  private final int codePoint;

  public UtfCodePoint(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
