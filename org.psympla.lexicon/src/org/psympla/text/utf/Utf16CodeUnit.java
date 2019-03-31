package org.psympla.text.utf;

import org.psympla.text.TextUnit;

public class Utf16CodeUnit implements TextUnit {
  private final int codePoint;

  public Utf16CodeUnit(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
