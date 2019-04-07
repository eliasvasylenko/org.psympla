package org.topiello.constraint;

import java.util.stream.Stream;

import org.topiello.pattern.Variable;
import org.topiello.symbol.LexicalItem;

public interface Constraint {
  Stream<Variable> variables();

  Constraint withInstantiation(Variable variable, LexicalItem lexicalItem);

  Stream<Constructor<?>> constructors(Variable variable);

  ;
}
