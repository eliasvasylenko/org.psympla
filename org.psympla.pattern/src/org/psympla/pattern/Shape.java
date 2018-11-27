package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;

public abstract class Shape {
  private static final AnyShape ANY_SHAPE = new AnyShape();

  public static LiteralShape literal(LexicalItem lexicalItem) {
    return new LiteralShape(lexicalItem);
  }

  public static AnyShape any() {
    return ANY_SHAPE;
  }

  public static VariableShape variable(String name) {
    return new VariableShape(name);
  }

  public static CellShape cell(Shape car, Shape cdr) {
    return new CellShape(car, cdr);
  }
}
