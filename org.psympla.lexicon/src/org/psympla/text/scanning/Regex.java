package org.psympla.text.scanning;

import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.symbol.Cell;
import org.psympla.symbol.CellPattern;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.TextItem;
import org.psympla.text.TextUnit;

public class Regex<C extends TextUnit> extends ScanningLexicalClass<C> {
  public Regex(Symbol symbol, String pattern) {
    super(
        symbol,
        new TextScanner<>(input -> input.match(pattern).stream().mapToInt(i -> i)),
        new TextPrinter<>(LexicalItem::toString));
  }

  public Cell instance(String text) {
    // TODO validate against pattern
    return Cell.cons(symbol(), new TextItem(text));
  }

  public Pattern<Cell> instance(Pattern<TextItem> variable) {
    // TODO validate against pattern
    return new CellPattern(symbol(), variable);
  }
}
