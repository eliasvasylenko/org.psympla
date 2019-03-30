package org.psympla.text.ascii;

import static org.psympla.lexicon.CharacterConverter.toAndFrom;

import java.util.List;

import org.psympla.lexicon.CharacterSet;
import org.psympla.text.TextUnit;

public class AsciiCharacter implements TextUnit {
  public static final CharacterSet<AsciiCharacter> ASCII_CHARACTERS = new CharacterSet<>(
      toAndFrom(
          CharacterSet.BYTES,
          ascii -> List.of(ascii.code()).iterator(),
          bytes -> new AsciiCharacter(bytes.next())));

  private final byte code;

  public AsciiCharacter(byte code) {
    this.code = code;
  }

  public byte code() {
    return code;
  }
}
