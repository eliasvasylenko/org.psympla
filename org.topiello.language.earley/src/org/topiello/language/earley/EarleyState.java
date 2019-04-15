package org.topiello.language.earley;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import org.topiello.grammar.Rule;

public class EarleyState<T extends Rule<V>, V> {
  private final NavigableMap<Integer, EarleySet> sets = new TreeMap<>();

  public EarleyState(V startSymbol) {
    addSet(0);
  }

  private EarleySet addSet(int i) {
    EarleySet set = new EarleySet(i);
    sets.put(i, set);
    return set;
  }

  public Optional<EarleySet> nextSet() {
    return Optional.ofNullable(sets.pollFirstEntry()).map(Entry::getValue);
  }
}
