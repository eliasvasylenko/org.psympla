package org.psympla.text.utf;

import org.psympla.text.TextUnit;

public class UTFCodePoint implements TextUnit {
  private final int codePoint;

  public UTFCodePoint(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
