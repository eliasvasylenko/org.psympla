package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Rule {
  private final Pattern<Expression> pattern;
  private final List<Pattern<Expression>> production;

  public Rule(
      Symbol symbol,
      Sequence parameters,
      Collection<? extends Pattern<Expression>> production) {
    this.pattern = new Expression(symbol, parameters);
    this.production = new ArrayList<>(production);
  }

  public Pattern<Expression> pattern() {
    return pattern;
  }

  public Stream<Pattern<Expression>> production() {
    return production.stream();
  }
}
