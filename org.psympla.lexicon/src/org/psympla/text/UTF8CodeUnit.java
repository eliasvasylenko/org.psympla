package org.psympla.text;

public class UTF8CodeUnit implements TextUnit {
  private final int codePoint;

  public UTF8CodeUnit(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
