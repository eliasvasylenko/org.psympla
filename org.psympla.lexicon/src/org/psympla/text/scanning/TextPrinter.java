package org.psympla.text.scanning;

import java.util.function.Function;

import org.psympla.lexicon.Characters;
import org.psympla.lexicon.scanning.Printer;
import org.psympla.symbol.Cell;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Value;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public class TextPrinter<C extends TextUnit> implements Printer<C, Cell<Value<String>, Nil>> {
  private final Function<? super Value<String>, ? extends String> print;

  public TextPrinter(Function<? super Value<String>, ? extends String> print) {
    this.print = print;
  }

  @Override
  public Characters<C> print(Cell<Value<String>, Nil> parameter) {
    return new Text<>(print.apply(parameter.car()));
  }
}
