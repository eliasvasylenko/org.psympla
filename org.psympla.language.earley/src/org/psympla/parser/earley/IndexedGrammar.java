package org.psympla.parser.earley;

import java.util.HashMap;
import java.util.Map;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.symbol.Symbol;

public class IndexedGrammar {
  private final Grammar grammar;
  private final Map<Symbol, IndexedSymbol> symbols = new HashMap<>();

  public IndexedGrammar(Grammar grammar) {
    this.grammar = grammar;

    grammar.getRules().forEach(this::addRule);
    grammar.getTerminals().forEach(this::addTerminal);
  }

  private void addRule(Rule rule) {
    getSymbol(rule.symbol()).addRule(rule);
  }

  private void addTerminal(Symbol terminal) {
    getSymbol(terminal).setTerminal();
  }

  IndexedSymbol getSymbol(Symbol symbol) {
    return symbols.computeIfAbsent(symbol, IndexedSymbol::new);
  }
}
