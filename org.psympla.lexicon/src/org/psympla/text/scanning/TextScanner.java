package org.psympla.text.scanning;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.scanning.Scan;
import org.psympla.lexicon.scanning.Scanner;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public class TextScanner<C extends TextUnit> implements Scanner<C> {
  private final Function<? super Text<C>, ? extends IntStream> scan;

  public TextScanner(Function<? super Text<C>, ? extends IntStream> scan) {
    this.scan = scan;
  }

  @Override
  public Stream<Scan> scan(Sequence<C> characters) {
    return scan
        .apply(new Text<>(characters))
        .mapToObj(i -> Scan.forEvaluation(i, characters::subSequence));
  }
}
