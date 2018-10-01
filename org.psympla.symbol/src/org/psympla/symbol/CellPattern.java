package org.psympla.symbol;

import java.util.Optional;
import java.util.stream.Stream;

public class CellPattern implements Pattern<Cell> {
  private final Pattern<?> car;
  private final Pattern<?> cdr;

  public CellPattern(Pattern<?> car, Pattern<?> cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + car + " " + cdr + ")";
  }

  public Pattern<?> getCar() {
    return car;
  }

  public Pattern<?> getCdr() {
    return cdr;
  }

  @Override
  public Stream<Variable<?>> getVariables() {
    return Stream.concat(car.getVariables(), cdr.getVariables());
  }

  @Override
  public Cell construct(Instantiations instantiations) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Instantiations> destructure(Cell symbol) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CellPattern partiallyConstruct(PartialInstantiations instantiations) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Instantiations> partiallyDestructure(Pattern<Cell> pattern) {
    // TODO Auto-generated method stub
    return null;
  }
}
