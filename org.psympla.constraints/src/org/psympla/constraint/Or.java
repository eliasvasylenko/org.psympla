package org.psympla.constraint;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import org.psympla.pattern.Variable;
import org.psympla.symbol.LexicalItem;

public class Or implements Constraint {
  private final Set<Constraint> constraints;

  public Or(Constraint... constraints) {
    this.constraints = Set.of(constraints);
  }

  public Or(Collection<? extends Constraint> constraints) {
    this.constraints = Set.copyOf(constraints);
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
