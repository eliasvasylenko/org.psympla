package org.psympla.text.scanning;

import java.util.Optional;
import java.util.function.Predicate;

import org.psympla.lexicon.scanning.Printer;
import org.psympla.symbol.Cell;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Value;
import org.psympla.text.CharacterSet;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

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
    return print.test(string) ? Optional.of(characterSet.fromString(string)) : Optional.empty();
  }
}
