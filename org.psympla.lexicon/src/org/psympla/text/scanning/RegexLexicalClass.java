package org.psympla.text.scanning;

import static org.psympla.pattern.Patterns.cons;

import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.pattern.Cons;
import org.psympla.pattern.Literal;
import org.psympla.pattern.Patterns;
import org.psympla.pattern.Variable;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Value;
import org.psympla.text.TextUnit;

public class RegexLexicalClass<C extends TextUnit> extends ScanningLexicalClass<C, Value<String>> {
  public RegexLexicalClass(Symbol symbol, String pattern) {
    super(
        symbol,
        new TextScanner<>(input -> input.match(pattern).stream().mapToInt(i -> i)),
        new TextPrinter<>(LexicalItem::toString));
  }

  public Literal instance(String parameter) {
    return Patterns.literal(symbol().consOnto(new Value<>(parameter)));
  }

  public Cons instance(Variable parameter) {
    return cons(symbol(), parameter);
  }
}
