package org.topiello.constraint;

import java.util.Set;
import java.util.stream.Stream;

import org.topiello.pattern.Pattern;
import org.topiello.pattern.Variable;
import org.topiello.symbol.LexicalItem;

public class Match implements Constraint {
  private final Pattern leftShape;
  private final Pattern rightShape;
  private final Set<Variable> variables;

  public Match(Pattern leftShape, Pattern rightShape) {
    this.leftShape = leftShape;
    this.rightShape = rightShape;
    this.variables = null;
  }

  public Pattern leftShape() {
    return leftShape;
  }

  public Pattern rightShape() {
    return rightShape;
  }

  @Override
  public Stream<Variable> variables() {
    return variables.stream();
  }

  @Override
  public Constraint withInstantiation(Variable variable, LexicalItem lexicalItem) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<Constructor<?>> constructors(Variable variable) {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean isValid() {
    // TODO Auto-generated method stub
    return false;
  }
}
