package org.psympla.pattern;

public class CellShape extends Shape {
  private final Shape car;
  private final Shape cdr;

  public CellShape(Shape car, Shape cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public Shape car() {
    return car;
  }

  public Shape cdr() {
    return cdr;
  }

  @Override
  public String toString() {
    return "(" + carString() + " " + cdrString() + ")";
  }

  private String carString() {
    return car.toString();
  }

  private String cdrString() {
    if (cdr instanceof CellShape) {
      CellShape cdrCell = (CellShape) cdr;
      return ". " + cdrCell.car() + " " + cdrCell.cdr();
    } else {
      return cdr.toString();
    }
  }
}
