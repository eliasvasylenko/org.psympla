package org.psympla.text.scanning;

import static org.psympla.pattern.Patterns.literal;
import static org.psympla.pattern.Patterns.sequence;

import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Variable;
import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Value;
import org.psympla.text.TextUnit;

public class RegexLexicalClass<C extends TextUnit>
    extends ScanningLexicalClass<C, Cell<Value<String>, Nil>> {
  public RegexLexicalClass(Symbol symbol, String pattern) {
    super(
        symbol,
        new TextScanner<>(input -> input.match(pattern).stream().mapToInt(i -> i)),
        new TextPrinter<>(LexicalItem::toString));
  }

  public Pattern instance(String parameter) {
    return literal(Sequence.of(symbol(), new Value<>(parameter)));
  }

  public Pattern instance(Variable parameter) {
    return sequence(literal(symbol()), parameter);
  }
}
