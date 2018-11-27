package org.psympla.pattern;

import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Term;

public final class Patterns {
  private Patterns() {}

  public static <T extends LexicalItem> Pattern<T> literal(T lexicalItem) {
    return new Pattern<>(new LiteralShape(lexicalItem), Constraints.empty());
  }

  public static Pattern<Cell> cell(Pattern<?> car, Pattern<?> cdr) {
    return new Pattern<>(
        Shape.cell(car.shape(), cdr.shape()),
        Constraints.merge(car.constraints(), cdr.constraints()));
  }

  public static Pattern<Term> term(Symbol car) {
    return literal(car.consOnto(Nil.instance()));
  }

  public static Pattern<Term> term(Symbol car, Pattern<?> cdr) {
    return new Pattern<Term>(Shape.cell(Shape.literal(car), cdr.shape()), cdr.constraints());
  }

  public static Pattern<Term> term(Pattern<Symbol> car, Pattern<?> cdr) {
    return new Pattern<>(
        Shape.cell(car.shape(), cdr.shape()),
        Constraints.merge(car.constraints(), cdr.constraints()));
  }
}
