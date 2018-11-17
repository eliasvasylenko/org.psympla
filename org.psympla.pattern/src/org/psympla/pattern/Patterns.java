package org.psympla.pattern;

import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Term;

public final class Patterns {
  private Patterns() {}

  public static <T extends LexicalItem> Pattern<T> literal(T lexicalItem) {
    return new Literal<>(lexicalItem);
  }

  public static Pattern<Term> term(Symbol car) {
    return literal(car.consOnto(Nil.instance()));
  }

  public static Pattern<Term> term(Symbol car, Pattern<?> cdr) {
    return () -> car.consOnto(cdr.instantiate());
  }

  public static Pattern<Term> term(Pattern<Symbol> car, Pattern<?> cdr) {
    return () -> car.instantiate().consOnto(cdr.instantiate());
  }

  public static Pattern<Cell> cell(Pattern<?> car, Pattern<?> cdr) {
    return null;
  }
}
