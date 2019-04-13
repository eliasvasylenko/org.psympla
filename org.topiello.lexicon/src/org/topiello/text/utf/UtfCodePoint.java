package org.topiello.text.utf;

import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class UtfCodePoint implements TextUnit {
  public static final CharacterSet<UtfCodePoint> CODE_POINTS = new CharacterSet<UtfCodePoint>(
      UtfCodePoint::toChars,
      UtfCodePoint::fromChars);

  private static CharSequence toChars(Text<UtfCodePoint> text) {
    var codePoints = text.stream().mapToInt(c -> c.codePoint()).toArray();
    return new String(codePoints, 0, codePoints.length);
  }

  private static Text<UtfCodePoint> fromChars(CharSequence string) {
    return Text.fromStream(string.codePoints().mapToObj(UtfCodePoint::new));
  }

  private final int codePoint;

  public UtfCodePoint(int codePoint) {
    this.codePoint = codePoint;
  }

  public int codePoint() {
    return codePoint;
  }
}
