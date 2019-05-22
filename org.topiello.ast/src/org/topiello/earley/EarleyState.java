package org.topiello.earley;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.grammar.Variable;

public class EarleyState<T extends Product> {
  private static final Variable ACCEPT_SYMBOL = new Variable() {};

  private static class StartRule<T extends Product> implements Rule<T> {
    private final T startProduct;

    public StartRule(T startProduct) {
      this.startProduct = startProduct;
    }

    @Override
    public Variable variable() {
      return ACCEPT_SYMBOL;
    }

    @Override
    public int length() {
      return 1;
    }

    @Override
    public T product(int index) {
      if (index != 0) {
        throw new IndexOutOfBoundsException();
      }
      return startProduct;
    }
  }

  private final NavigableMap<Integer, EarleySet> sets = new TreeMap<>();

  public EarleyState(T startProduct) {
    // getSet(0).getItem(new LR0Item(new StartRule<>(startProduct), 0));
  }

  private EarleySet getSet(int index) {
    return sets.computeIfAbsent(index, i -> new EarleySet(null, i));
  }

  public Optional<EarleySet> nextSet() {
    return Optional.ofNullable(sets.pollFirstEntry()).map(Entry::getValue);
  }
}
