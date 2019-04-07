package org.topiello.language.earley;

import java.util.Optional;

import org.topiello.symbol.LexicalItem;
import org.topiello.symbol.Symbol;

public class EarleyParser {
  private final EarleyState state;

  public EarleyParser(Symbol startSymbol, Iterable<LexicalItem> items) {
    state = new EarleyState(startSymbol);

    Optional<EarleySet> nextSet;
    while ((nextSet = state.nextSet()).isPresent()) {
      var set = nextSet.get();
      predict(set);
    }
  }

  private void predict(EarleySet set) {
    // TODO predict (and scan & complete over empties) recursively

    set.nodes().forEach(node -> {});
  }
}
