package org.psympla.parser.earley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.psympla.grammar.Rule;
import org.psympla.symbol.Symbol;

public class IndexedSymbol {
  private final Symbol symbol;
  private final List<Rule> rules = new ArrayList<>();

  IndexedSymbol(Symbol symbol) {
    this.symbol = symbol;
  }

  void addRule(Rule rule) {
    rules.add(rule);
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public List<Rule> getRules() {
    return Collections.unmodifiableList(rules);
  }
}
