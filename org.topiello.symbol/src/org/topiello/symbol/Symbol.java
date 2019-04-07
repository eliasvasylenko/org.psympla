package org.topiello.symbol;

import java.util.Objects;

/**
 * A symbol.
 * 
 * @author Elias N Vasylenko
 */
public class Symbol implements Atom<Symbol> {
  private final String name;

  public Symbol(String name) {
    this.name = name;
  }

  /**
   * @return the name of the symbol
   */
  public String name() {
    return name;
  }

  @Override
  public <T extends Sequence> Cell<Symbol, T> consOnto(T item) {
    return new Cell<>(this, item);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + name + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof Symbol))
      return false;

    Symbol that = (Symbol) obj;

    return Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
