package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Cell;

public class Rule {
  private final Pattern<Cell> pattern;
  private final List<Pattern<Cell>> production;

  public Rule(
      Symbol symbol,
      Pattern<Cell> parameters,
      Collection<? extends Pattern<Cell>> production) {
    this.pattern = Cell.pattern(symbol, parameters);
    this.production = new ArrayList<>(production);
  }

  public Pattern<Cell> pattern() {
    return pattern;
  }

  public Stream<Pattern<Cell>> production() {
    return production.stream();
  }
}
