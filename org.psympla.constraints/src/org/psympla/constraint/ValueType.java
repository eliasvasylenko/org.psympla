package org.psympla.constraint;

import java.util.stream.Stream;

import org.psympla.pattern.Variable;
import org.psympla.symbol.LexicalItem;

public class ValueType<T> implements Constraint {
  public ValueType(Variable variable, Class<T> type) {
    // TODO Auto-generated constructor stub
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
