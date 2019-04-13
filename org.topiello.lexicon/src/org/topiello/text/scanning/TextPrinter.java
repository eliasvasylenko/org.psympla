package org.topiello.text.scanning;

import java.util.Optional;
import java.util.function.Predicate;

import org.topiello.lexicon.scanning.Printer;
import org.topiello.symbol.Cell;
import org.topiello.symbol.Nil;
import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class TextPrinter<C extends TextUnit> implements Printer<C, Cell<Value<String>, Nil>> {
  private final CharacterSet<C> characterSet;
  private final Predicate<? super String> print;

  public TextPrinter(CharacterSet<C> characterSet, Predicate<? super String> print) {
    this.characterSet = characterSet;
    this.print = print;
  }

  @Override
  public Optional<Text<C>> print(Cell<Value<String>, Nil> parameter) {
    var string = parameter.car().get();
    return print.test(string) ? Optional.of(characterSet.fromChars(string)) : Optional.empty();
  }
}
