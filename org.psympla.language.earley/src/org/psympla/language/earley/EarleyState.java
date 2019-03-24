package org.psympla.language.earley;

import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.psympla.grammar.Rule;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.Symbol;

public class EarleyState {
  private final SortedMap<Integer, EarleySet> sets = new TreeMap<>();

  public EarleyState(Symbol startSymbol) {
    addSet(0).addItem(new LR0Item(new Rule(Patterns.sequence()).withProduct(startSymbol), 0), 0);
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
