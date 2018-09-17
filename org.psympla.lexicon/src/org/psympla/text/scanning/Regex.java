package org.psympla.text.scanning;

import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;
import org.psympla.text.Text;
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
    return Cell.cons(symbol(), new Text<>(text));
  }

  public Pattern<Cell> instance(Pattern<Sequence<C>> variable) {
    // TODO validate against pattern
    return Cell.pattern(symbol(), variable);
  }
}
