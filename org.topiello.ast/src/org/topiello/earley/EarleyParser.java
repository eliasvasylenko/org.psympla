package org.topiello.earley;

import java.util.Optional;

import org.topiello.grammar.Product;

public class EarleyParser<T extends Product> {
  private final EarleyState<T> state;

  public EarleyParser(T startSymbol) {
    state = new EarleyState<>(startSymbol);

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
