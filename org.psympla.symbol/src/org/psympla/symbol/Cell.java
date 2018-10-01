package org.psympla.symbol;

import java.util.stream.Stream;

public class Cell extends CellPattern implements Sequence<Cell> {
  private static final Cell EMPTY = null; // TODO new Cell(emptyList());

  public Cell(LexicalItem<?> car, LexicalItem<?> cdr) {
    super(car, cdr);
  }

  @Override
  public LexicalItem<?> getCar() {
    return (LexicalItem<?>) super.getCar();
  }

  @Override
  public LexicalItem<?> getCdr() {
    return (LexicalItem<?>) super.getCdr();
  }

  public static Cell cons(LexicalItem<?> car, LexicalItem<?> cdr) {
    return EMPTY;
  }

  @Override
  public boolean isAtomic() {
    return false;
  }

  @Override
  public CellPattern partiallyConstruct(PartialInstantiations instantiations) {
    // TODO Auto-generated method stub
    return super.partiallyConstruct(instantiations);
  }

  @Override
  public Stream<LexicalItem<?>> elements() {
    return getCdr() instanceof Cell
        ? Stream.concat(Stream.of(getCar()), ((Cell) getCdr()).elements())
        : Stream.of(getCar());
  }

  @Override
  public LexicalItem<?> terminator() {
    return getCdr() instanceof Sequence ? ((Sequence<?>) getCdr()).terminator() : getCdr();
  }

  @Override
  public boolean isProper() {
    return getCdr() instanceof Sequence && ((Sequence<?>) getCdr()).isProper();
  }
}
