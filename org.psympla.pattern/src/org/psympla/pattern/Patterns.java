package org.psympla.pattern;

import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Nil;

public final class Patterns {
  private static final Wildcard WILDCARD = new Wildcard();
  private static final Literal NIL = new Literal(Nil.instance());

  private Patterns() {}

  public static Cons cons(Pattern car, Pattern cdr) {
    return new Cons(car, cdr);
  }

  public static Cons cons(LexicalItem car, Pattern cdr) {
    return new Cons(literal(car), cdr);
  }

  public static Cons cons(Pattern car, LexicalItem cdr) {
    return new Cons(car, literal(cdr));
  }

  public static Literal literal(LexicalItem lexicalItem) {
    return new Literal(lexicalItem);
  }

  public static final Wildcard wildcard() {
    return WILDCARD;
  }

  public static final Literal nil() {
    return NIL;
  }
}
