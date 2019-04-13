package org.topiello.language.earley;

import java.util.Optional;

import org.topiello.grammar.Rule;

public class EarleyParser<T extends Rule<V>, V> {
  private final EarleyState<T, V> state;

  public EarleyParser(V startSymbol) {
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
