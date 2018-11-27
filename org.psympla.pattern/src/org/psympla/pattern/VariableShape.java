package org.psympla.pattern;

public class VariableShape extends Shape {
  private final String name;

  VariableShape(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
