package org.psympla.symbol;

import java.util.stream.Stream;

public class Cell implements Sequence {
  private final LexicalItem car;
  private final LexicalItem cdr;

  protected Cell(LexicalItem car, LexicalItem cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public LexicalItem car() {
    return car;
  }

  public LexicalItem cdr() {
    return cdr;
  }

  @Override
  public Stream<LexicalItem> elements() {
    return cdr() instanceof Cell
        ? Stream.concat(Stream.of(car()), ((Cell) cdr()).elements())
        : Stream.of(car());
  }

  @Override
  public boolean isProper() {
    return cdr() instanceof Sequence && ((Sequence) cdr()).isProper();
  }
}
