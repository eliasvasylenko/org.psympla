package org.psympla.text.scanning;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.psympla.lexicon.scanning.Scan;
import org.psympla.lexicon.scanning.Scanner;
import org.psympla.symbol.Cell;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Value;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public class TextScanner<C extends TextUnit> implements Scanner<C, Cell<Value<String>, Nil>> {
  private final Function<? super Text<C>, ? extends IntStream> scan;

  public TextScanner(Function<? super Text<C>, ? extends IntStream> scan) {
    this.scan = scan;
  }

  @Override
  public Stream<Scan<Cell<Value<String>, Nil>>> scan(Text<C> characters) {
    return scan
        .apply(characters)
        .mapToObj(
            i -> Scan
                .forParameter(
                    i,
                    new Value<String>(characters.subSequence(i).toString())
                        .consOnto(Sequence.empty())));
  }
}
