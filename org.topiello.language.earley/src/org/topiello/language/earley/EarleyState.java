package org.topiello.language.earley;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import org.topiello.grammar.Rule;

public class EarleyState<T extends Rule<V>, V> {
  private final NavigableMap<Integer, EarleySet> sets = new TreeMap<>();

  public EarleyState(V startSymbol) {
    getSet(0).addItem(lr0Item, 0);
  }

  private EarleySet getSet(int i) {
    return sets.computeIfAbsent(i, EarleySet::new);
  }

  public Optional<EarleySet> nextSet() {
    return Optional.ofNullable(sets.pollFirstEntry()).map(Entry::getValue);
  }
}
