package org.topiello.semantics;

import java.util.stream.Stream;

import org.topiello.grammar.Rule;

public interface Semantics<U extends Rule<?>> {
  Stream<Denotation<?, U>> getDenotation(U rule);
}
