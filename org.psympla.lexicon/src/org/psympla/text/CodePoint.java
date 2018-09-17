package org.psympla.text;

public class CodePoint implements TextUnit {
  private final int codePoint;

  public CodePoint(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
