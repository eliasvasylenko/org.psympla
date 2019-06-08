package org.topiello.earley;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import org.topiello.grammar.Product;

public class EarleyState<T extends Product> {
  private final NavigableMap<Integer, EarleySet> sets = new TreeMap<>();

  public EarleySet getSet(int index) {
    return sets.computeIfAbsent(index, i -> new EarleySet(i));
  }

  public Optional<EarleySet> nextSet() {
    return Optional.ofNullable(sets.pollFirstEntry()).map(Entry::getValue);
  }
}
