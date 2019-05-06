package org.topiello.language.earley;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.language.earley.index.LR0Item;

public class EarleyState<T extends Rule<V>, V> {
  private static final Object ACCEPT_SYMBOL = new Object();

  private static class StartRule<T> implements Rule<Object> {
    private final Product<T> startProduct;

    public StartRule(Product<T> startProduct) {
      this.startProduct = startProduct;
    }

    @Override
    public Object variable() {
      return ACCEPT_SYMBOL;
    }

    @Override
    public int length() {
      return 1;
    }

    @Override
    public Product<T> product(int index) {
      return startProduct;
    }
  }

  private final NavigableMap<Integer, EarleySet> sets = new TreeMap<>();

  public EarleyState(Product<T> startProduct) {
    getSet(0).getItem(new LR0Item(new StartRule<>(startProduct), 0));
  }

  private EarleySet getSet(int i) {
    return sets.computeIfAbsent(i, EarleySet::new);
  }

  public Optional<EarleySet> nextSet() {
    return Optional.ofNullable(sets.pollFirstEntry()).map(Entry::getValue);
  }
}
