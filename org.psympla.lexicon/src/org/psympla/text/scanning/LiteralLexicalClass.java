package org.psympla.text.scanning;

import static org.psympla.pattern.Patterns.literal;

import java.util.stream.IntStream;

import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.pattern.Pattern;
import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Value;
import org.psympla.text.TextUnit;

public class LiteralLexicalClass<C extends TextUnit>
    extends ScanningLexicalClass<C, Cell<Value<String>, Nil>> {
  private final String value;

  public LiteralLexicalClass(Symbol symbol, String value) {
    super(
        symbol,
        new TextScanner<>(
            input -> input.startsWith(value) ? IntStream.of(value.length()) : IntStream.empty()),
        new TextPrinter<>(LexicalItem::toString));
    this.value = value;
  }

  public Pattern instance() {
    return literal(Sequence.of(symbol(), value()));
  }

  public Value<String> value() {
    return new Value<>(this.value);
  }
}
