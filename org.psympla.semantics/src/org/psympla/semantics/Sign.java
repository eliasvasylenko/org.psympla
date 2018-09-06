package org.psympla.semantics;

import org.psympla.symbol.Cell;
import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;

/**
 * A sign represents a class of lexical elements which can be assigned a
 * meaning.
 * <p>
 * Not all symbols need to be signified. For example delimiter terminals or
 * logical non-terminals such as aggregators with no useful state of their own.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 */
public class Sign<T> {
  private final Symbol name;
  private final Pattern<Cell> value;

  public Sign(Symbol name, Pattern<Cell> value) {
    this.name = name;
    this.value = value;
  }

  public Symbol name() {
    return name;
  }

  public Pattern<Cell> valuePattern() {
    return value;
  }
}
