package org.topiello.grammar.contextfree;

public class Symbol {
  private final String name;

  public Symbol(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + name + ")";
  }
}
