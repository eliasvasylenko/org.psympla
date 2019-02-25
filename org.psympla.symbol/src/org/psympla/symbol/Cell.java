package org.psympla.symbol;

import java.util.stream.Stream;

public class Cell<T extends LexicalItem, U extends Sequence> implements Sequence {
  private final T car;
  private final U cdr;

  protected Cell(T car, U cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public T car() {
    return car;
  }

  public U cdr() {
    return cdr;
  }

  @Override
  public Stream<LexicalItem> elements() {
    return cdr() instanceof Cell
        ? Stream.concat(Stream.of(car()), ((Cell<?, ?>) cdr()).elements())
        : Stream.of(car());
  }

  @Override
  public <V extends Sequence> Cell<Cell<T, U>, V> consOnto(V item) {
    return new Cell<>(this, item);
  }
}
