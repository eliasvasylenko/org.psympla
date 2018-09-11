package org.psympla.grammar;

import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;

public class Rule {
  private final Symbol symbol;
  private final Pattern<? extends LexicalItem<?>> parameter;
  private final Production production;

  public Rule(Symbol symbol, Pattern<? extends LexicalItem<?>> parameter, Production production) {
    this.symbol = symbol;
    this.parameter = parameter;
    this.production = production;
  }

  public Symbol symbol() {
    return symbol;
  }

  public Pattern<? extends LexicalItem<?>> parameter() {
    return parameter;
  }

  public Pattern<Cell> pattern() {
    return Cell.pattern(parameter, symbol);
  }

  public Production production() {
    return production;
  }
}
