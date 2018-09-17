package org.psympla.text;

public class AsciiCharacter implements TextUnit {
  private final byte code;

  public AsciiCharacter(byte code) {
    this.code = code;
  }

  public byte code() {
    return code;
  }
}
