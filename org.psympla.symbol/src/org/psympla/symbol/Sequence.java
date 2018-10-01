package org.psympla.symbol;

import java.util.stream.Stream;

public interface Sequence<T extends Sequence<T>> extends LexicalItem<T> {
  Stream<LexicalItem<?>> elements();

  LexicalItem<?> terminator();

  boolean isProper();
}
