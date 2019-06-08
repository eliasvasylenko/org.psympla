package org.topiello.pattern.constraint;

import java.util.stream.Stream;

import org.topiello.pattern.Pattern;
import org.topiello.pattern.Variable;
import org.topiello.pattern.symbol.LexicalItem;

public class RegexConstraint implements Constraint {
  public RegexConstraint(String regex, Pattern pattern) {

  }

  @Override
  public Stream<Variable> variables() {
    // TODO Auto-generated method stub
    return null;
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

}
