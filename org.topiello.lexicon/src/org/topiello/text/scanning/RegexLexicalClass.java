package org.topiello.text.scanning;

import static org.topiello.pattern.Patterns.literal;
import static org.topiello.pattern.Patterns.sequence;

import org.topiello.lexicon.scanning.ScanningLexicalClass;
import org.topiello.pattern.Pattern;
import org.topiello.pattern.Variable;
import org.topiello.symbol.Cell;
import org.topiello.symbol.Nil;
import org.topiello.symbol.Sequence;
import org.topiello.symbol.Symbol;
import org.topiello.symbol.Value;
import org.topiello.text.TextUnit;

public class RegexLexicalClass<C extends TextUnit>
    extends ScanningLexicalClass<C, Cell<Value<String>, Nil>> {
  public RegexLexicalClass(Symbol symbol, String pattern) {
    super(symbol, null, null);
  }

  public Pattern instance(String parameter) {
    return literal(Sequence.of(symbol(), new Value<>(parameter)));
  }

  public Pattern instance(Variable parameter) {
    return sequence(literal(symbol()), parameter);
  }
}
