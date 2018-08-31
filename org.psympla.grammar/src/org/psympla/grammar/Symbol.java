package org.psympla.grammar;

public class Symbol implements LexicalItem<Symbol> {
  private final String id;

  public Symbol(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + id + ")";
  }
}
