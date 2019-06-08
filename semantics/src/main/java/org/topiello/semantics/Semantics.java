package org.topiello.semantics;

import java.util.stream.Stream;

import org.topiello.grammar.Product;

public interface Semantics<U extends Product> {
  Stream<? extends Denotation<?, U>> getDenotation(U rule);
}
