package org.psympla.parser.index;

import static java.util.stream.Stream.concat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.psympla.pattern.Cons;
import org.psympla.pattern.Literal;
import org.psympla.pattern.Pattern;
import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;

public class Index<T> {
  private static class PatternIndex {
    private final boolean parametric;
    private final Optional<Symbol> symbol;

    public PatternIndex(Pattern pattern) {
      LexicalItem identifier;

      if (pattern.getClass() == Literal.class) {
        var lexicalItem = ((Literal) pattern).lexicalItem();

        this.parametric = lexicalItem.getClass() == Cell.class;
        identifier = parametric ? ((Cell<?, ?>) lexicalItem).car() : lexicalItem;

      } else {
        Pattern identifierPattern;

        this.parametric = pattern.getClass() == Cons.class;
        identifierPattern = parametric ? ((Cons) pattern).car() : pattern;
        identifier = identifierPattern instanceof Literal
            ? ((Literal) identifierPattern).lexicalItem()
            : null;
      }

      this.symbol = identifier instanceof Symbol
          ? Optional.of((Symbol) identifier)
          : Optional.empty();
    }
  }

  private final Map<Symbol, List<T>> sequences = new HashMap<>();
  private final Map<Symbol, List<T>> singles = new HashMap<>();

  protected Index(
      Supplier<? extends Stream<? extends T>> items,
      Function<? super T, ? extends Pattern> patternFunction) {
    items.get().forEach(item -> addItem(item, patternFunction));
  }

  protected void addItem(T item, Function<? super T, ? extends Pattern> patternFunction) {
    Pattern pattern = patternFunction.apply(item);
    var index = new PatternIndex(pattern);

    (index.parametric ? sequences : singles)
        .computeIfAbsent(index.symbol.orElse(null), s -> new ArrayList<>())
        .add(item);
  }

  public Stream<T> getItems(Pattern pattern) {
    var index = new PatternIndex(pattern);

    return index.symbol
        .map(s -> getItems(s, index.parametric))
        .orElseGet(() -> getItems(index.parametric));
  }

  private Stream<T> getItems(Symbol symbol, boolean parametric) {
    return concat(
        (parametric ? sequences : singles).get(symbol).stream(),
        Stream.of(sequences.get(null), singles.get(null)).flatMap(List::stream));
  }

  private Stream<T> getItems(boolean parametric) {
    return parametric
        ? concat(singles.get(null).stream(), sequences.values().stream().flatMap(List::stream))
        : Stream
            .of(singles.values(), sequences.values())
            .flatMap(Collection::stream)
            .flatMap(List::stream);
  }

  @Override
  public String toString() {
    return singles.toString() + sequences.toString();
  }
}
