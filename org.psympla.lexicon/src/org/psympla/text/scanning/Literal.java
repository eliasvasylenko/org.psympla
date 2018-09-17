package org.psympla.text.scanning;

import java.util.stream.IntStream;

import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;
import org.psympla.text.TextUnit;

public class Literal<C extends TextUnit> extends ScanningLexicalClass<C> {
  public Literal(Symbol symbol, String literal) {
    super(
        symbol,
        new TextScanner<>(
            input -> input.startsWith(literal)
                ? IntStream.of(literal.length())
                : IntStream.empty()),
        new TextPrinter<>(LexicalItem::toString));
  }
}
