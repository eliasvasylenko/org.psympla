package org.psympla.lexicon;

import java.util.stream.Stream;

import org.psympla.grammar.Expression;

public interface Token<C> {
  Expression symbol();

  Stream<C> characters();
}
