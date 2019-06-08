package org.topiello.text.ascii;

import org.topiello.text.CharacterSet;
import org.topiello.text.TextUnit;

public class AsciiCharacter implements TextUnit {
  public static final CharacterSet<AsciiCharacter> ASCII_CHARACTERS = null;

  private final byte code;

  public AsciiCharacter(byte code) {
    this.code = code;
  }

  public byte code() {
    return code;
  }
}
