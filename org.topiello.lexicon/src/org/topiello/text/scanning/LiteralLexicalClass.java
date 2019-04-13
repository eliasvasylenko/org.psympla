package org.topiello.text.scanning;

import static org.topiello.pattern.Patterns.literal;

import java.util.stream.IntStream;

import org.topiello.lexicon.scanning.ScanningLexicalClass;
import org.topiello.pattern.Pattern;
import org.topiello.symbol.Cell;
import org.topiello.symbol.Nil;
import org.topiello.symbol.Sequence;
import org.topiello.symbol.Symbol;
import org.topiello.symbol.Value;
import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class LiteralLexicalClass<C extends TextUnit>
    extends ScanningLexicalClass<C, Cell<Value<String>, Nil>> {
  private final String value;

  public LiteralLexicalClass(CharacterSet<C> characterSet, Symbol symbol, String value) {
    this(characterSet, symbol, value, characterSet.fromChars(value));
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
