package org.topiello.text.utf;

import org.topiello.text.TextUnit;

public class Utf8CodeUnit implements TextUnit {
  private final int codePoint;

  public Utf8CodeUnit(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
