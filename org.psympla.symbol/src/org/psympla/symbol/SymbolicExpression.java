package org.psympla.symbol;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;

import java.util.Objects;
import java.util.stream.Stream;

/*
 * 
 * 
 * 
 * 
 * 
 * TODO this naming is wrong. An atom is also an S-Expression!
 * 
 *  
 *  So what to term this? A cons cell?
 *  
 *  Can we find better terminology that fits with the domain?
 *  
 *  Do we really want to abandon the stronger typing of having a "Sequence" and a "Term", where
 *  the latter is the same as the former but always headed by a symbol?
 *  
 *  Do we need a more complicated pattern API to support things like ranges? 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class SymbolicExpression implements LexicalItem<SymbolicExpression> {
  private static final SymbolicExpression EMPTY = new SymbolicExpression(emptyList());

  private final LexicalItem<?> car;
  private final LexicalItem<?> cdr;

  public SymbolicExpression(LexicalItem<?> car, LexicalItem<?> cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public Stream<LexicalItem<?>> getElements() {
    return null;
  }

  static SymbolicExpression cons(LexicalItem<?> car, LexicalItem<?> cdr) {
    return EMPTY;
  }

  static SymbolicExpression empty() {
    return EMPTY;
  }

  public static Pattern<SymbolicExpression> pattern(Pattern<?> car, Pattern<?> cdr) {
    return null; // TODO
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + getElements().map(Objects::toString).collect(joining("(", " ", ")"));
  }
}
