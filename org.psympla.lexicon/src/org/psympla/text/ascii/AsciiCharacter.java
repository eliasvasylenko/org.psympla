package org.psympla.text.ascii;

import org.psympla.text.CharacterSet;
import org.psympla.text.TextUnit;

public class AsciiCharacter implements TextUnit {
  public static final CharacterSet<AsciiCharacter> ASCII_CHARACTERS = new CharacterSet<>();

  private final byte code;

  public AsciiCharacter(byte code) {
    this.code = code;
  }

  public byte code() {
    return code;
  }
}
