package org.psympla.text.scanning;

import java.util.function.Function;

import org.psympla.lexicon.scanning.Printer;
import org.psympla.symbol.Cell;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Value;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public class TextPrinter<C extends TextUnit> implements Printer<C, Cell<Value<String>, Nil>> {
  private final Function<? super Value<String>, ? extends Text<C>> print;

  public TextPrinter(Function<? super Value<String>, ? extends Text<C>> print) {
    this.print = print;
  }

  @Override
  public Text<C> print(Cell<Value<String>, Nil> parameter) {
    return print.apply(parameter.car());
  }
}
