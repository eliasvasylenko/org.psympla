package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Rule {
  private final Pattern<Term> pattern;
  private final List<Pattern<Term>> production;

  public Rule(
      Symbol symbol,
      Sequence parameters,
      Collection<? extends Pattern<Term>> production) {
    this.pattern = new Term(symbol, parameters);
    this.production = new ArrayList<>(production);
  }

  public Pattern<Term> pattern() {
    return pattern;
  }

  public Stream<Pattern<Term>> production() {
    return production.stream();
  }
}
