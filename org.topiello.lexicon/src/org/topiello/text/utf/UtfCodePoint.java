package org.topiello.text.utf;

import org.topiello.text.CharacterSet;
import org.topiello.text.TextUnit;

public class UtfCodePoint implements TextUnit {
  public static final CharacterSet<UtfCodePoint> CODE_POINTS = null;

  private final int codePoint;

  public UtfCodePoint(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
