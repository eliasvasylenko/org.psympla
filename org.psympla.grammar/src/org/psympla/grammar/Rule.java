package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.SymbolicExpression;

public class Rule {
  private final Pattern<SymbolicExpression> pattern;
  private final List<Pattern<SymbolicExpression>> production;

  public Rule(
      Symbol symbol,
      Pattern<SymbolicExpression> parameters,
      Collection<? extends Pattern<SymbolicExpression>> production) {
    this.pattern = SymbolicExpression.pattern(symbol, parameters);
    this.production = new ArrayList<>(production);
  }

  public Pattern<SymbolicExpression> pattern() {
    return pattern;
  }

  public Stream<Pattern<SymbolicExpression>> production() {
    return production.stream();
  }
}
