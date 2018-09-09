package org.psympla.symbol;

import static java.util.stream.Collectors.joining;

import java.util.Objects;
import java.util.stream.Stream;

/*
 * 
 * 
 * 
 * 
 * 
 * TODO Can we find better terminology that fits with the domain?
 *  
 * Do we really want to abandon the stronger typing of having a "Sequence" and a "Term", where
 * the latter is the same as the former but always headed by a symbol?
 *  
 * Do we need a more complicated pattern API to support things like ranges? 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class Cell implements LexicalItem<Cell> {
  private static final Cell EMPTY = null; // TODO new Cell(emptyList());

  private final LexicalItem<?> car;
  private final LexicalItem<?> cdr;

  public Cell(LexicalItem<?> car, LexicalItem<?> cdr) {
    this.car = car;
    this.cdr = cdr;
  }

  public Stream<LexicalItem<?>> getElements() {
    return null;
  }

  static Cell cons(LexicalItem<?> car, LexicalItem<?> cdr) {
    return EMPTY;
  }

  static Cell empty() {
    return EMPTY;
  }

  public static Pattern<Cell> pattern(Pattern<?> car, Pattern<?> cdr) {
    return null; // TODO
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + getElements().map(Objects::toString).collect(joining("(", " ", ")"));
  }
}
