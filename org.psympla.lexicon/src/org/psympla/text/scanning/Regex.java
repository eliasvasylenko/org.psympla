package org.psympla.text.scanning;

import static org.psympla.pattern.Patterns.literal;
import static org.psympla.pattern.Patterns.term;

import org.psympla.lexicon.scanning.ScanningLexicalClass;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Variable;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Term;
import org.psympla.symbol.Value;
import org.psympla.text.TextUnit;

public class Regex<C extends TextUnit> extends ScanningLexicalClass<C> {
  public Regex(Symbol symbol, String pattern) {
    super(
        symbol,
        new TextScanner<>(input -> input.match(pattern).stream().mapToInt(i -> i)),
        new TextPrinter<>(LexicalItem::toString));
  }

  public Pattern<Term> instance(String text) {
    return literal(symbol().consOnto(new Value<>(text)));
  }

  public Pattern<Term> instance(Variable<Value<String>> variable) {
    return term(symbol(), variable);
  }
}
