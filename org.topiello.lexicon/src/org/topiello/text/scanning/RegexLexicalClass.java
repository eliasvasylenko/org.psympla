package org.topiello.text.scanning;

import static org.topiello.pattern.Patterns.literal;
import static org.topiello.pattern.Patterns.sequence;

import javax.sound.midi.Sequence;

import org.topiello.lexicon.scanning.ScanningLexicalClass;
import org.topiello.symbol.Cell;
import org.topiello.symbol.Nil;
import org.topiello.text.TextUnit;

public class RegexLexicalClass<T, C extends TextUnit>
    extends ScanningLexicalClass<Cell<Value<String>, Nil>, C> {
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
