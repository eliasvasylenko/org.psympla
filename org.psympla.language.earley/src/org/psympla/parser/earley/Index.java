package org.psympla.parser.earley;

import java.util.Optional;

import org.psympla.pattern.Cons;
import org.psympla.pattern.Pattern;
import org.psympla.symbol.Symbol;

public class Index {
  private final Optional<Symbol> symbol;
  private final boolean parametric;

  public Index(Pattern pattern) {
    this.parametric = pattern instanceof Cons;
    var identifier = parametric ? ((Cons) pattern).car() : pattern;
    this.symbol = identifier instanceof Symbol
        ? Optional.ofNullable((Symbol) identifier)
        : Optional.empty();
  }

  public Optional<Symbol> getSymbol() {
    return symbol;
  }

  public boolean isParametric() {
    return parametric;
  }
}
