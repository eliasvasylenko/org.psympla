package org.psympla.text;

public class UTF16CodeUnit implements TextUnit {
  private final int codePoint;

  public UTF16CodeUnit(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
