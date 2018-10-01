package org.psympla.example.expression;

public class Var implements Expression {
  private final String name;

  public Var(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }
}
