package org.psympla.text.scanning;

import java.util.function.Function;

import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.scanning.Printer;
import org.psympla.symbol.LexicalItem;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public class TextPrinter<C extends TextUnit> implements Printer<C> {
  private final Function<? super LexicalItem<?>, ? extends String> print;

  public TextPrinter(Function<? super LexicalItem<?>, ? extends String> print) {
    this.print = print;
  }

  @Override
  public Sequence<C> print(LexicalItem<?> parameter) {
    return new Text<>(print.apply(parameter));
  }
}
