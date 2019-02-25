package org.psympla.symbol;

import java.util.stream.Stream;

public interface Sequence extends LexicalItem {
  Stream<LexicalItem> elements();
}
