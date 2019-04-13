package org.topiello.text.scanning;

import static org.topiello.pattern.Patterns.literal;

import java.util.stream.IntStream;

import javax.sound.midi.Sequence;

import org.topiello.lexicon.scanning.ScanningLexicalClass;
import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public abstract class LiteralLexicalClass<T, C extends TextUnit>
    extends ScanningLexicalClass<T, C> {
  private final String value;

  public LiteralLexicalClass(CharacterSet<C> characterSet, T variable, String value) {
    this(characterSet, variable, value, characterSet.fromChars(value));
  }

  private LiteralLexicalClass(
      CharacterSet<C> characterSet,
      T variable,
      String stringValue,
      Text<C> textValue) {
    super(
        variable,
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

  public abstract T getVariable(String string);

  public abstract String getVariable(T variable);
}
