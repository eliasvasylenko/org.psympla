package org.psympla.constraint;

import java.util.stream.Stream;

import org.psympla.pattern.Variable;
import org.psympla.symbol.LexicalItem;

public interface Constraint {
  Stream<Variable> variables();

  Constraint withInstantiation(Variable variable, LexicalItem lexicalItem);

  Stream<Constructor<?>> constructors(Variable variable);

  ;
}
