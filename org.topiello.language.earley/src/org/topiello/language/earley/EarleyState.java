package org.topiello.language.earley;

import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.topiello.grammar.Rule;

public class EarleyState<T extends Rule<V>, V> {
  private final SortedMap<Integer, EarleySet> sets = new TreeMap<>();

  public EarleyState(V startSymbol) {
    addSet(0);
  }

  private EarleySet addSet(int i) {
    EarleySet set = new EarleySet(i);
    sets.put(i, set);
    return set;
  }

  public Optional<EarleySet> nextSet() {
    return sets.isEmpty()
        ? Optional.empty()
        : Optional.of(sets.entrySet().iterator().next().getValue());
  }
}
