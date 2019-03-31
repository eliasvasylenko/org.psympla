package org.psympla.text.scanning;

import static org.psympla.pattern.Patterns.literal;

import java.util.stream.IntStream;

import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.pattern.Pattern;
import org.psympla.symbol.Cell;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Value;
import org.psympla.text.CharacterSet;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public class LiteralLexicalClass<C extends TextUnit>
    extends ScanningLexicalClass<C, Cell<Value<String>, Nil>> {
  private final String value;

  public LiteralLexicalClass(CharacterSet<C> characterSet, Symbol symbol, String value) {
    this(characterSet, symbol, value, characterSet.fromString(value));
  }

  private LiteralLexicalClass(
      CharacterSet<C> characterSet,
      Symbol symbol,
      String stringValue,
      Text<C> textValue) {
    super(
        symbol,
        new TextScanner<>(
            characterSet,
            input -> input.startsWith(textValue)
                ? IntStream.of(stringValue.length())
                : IntStream.empty()),
        new TextPrinter<>(characterSet, stringValue::equals));
    this.value = stringValue;
  }

  public Pattern instance() {
    return literal(Sequence.of(symbol(), value()));
  }

  public Value<String> value() {
    return new Value<>(this.value);
  }
}
