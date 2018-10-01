package org.psympla.parser.earley;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.symbol.Symbol;

public class IndexedGrammar {
  private final Grammar grammar;
  private final Map<Symbol, IndexedSymbol> symbols = new HashMap<>();

  public IndexedGrammar(Grammar grammar) {
    this.grammar = grammar;

    grammar.getRules().forEach(this::addRule);
  }

  private void addRule(Rule rule) {
    symbols.computeIfAbsent(rule.symbol(), IndexedSymbol::new).addRule(rule);
  }

  public IndexedSymbol getSymbol(Symbol symbol) {
    var indexed = symbols.get(symbol);
    return indexed == null ? new IndexedSymbol(symbol) : indexed;
  }

  public Stream<Symbol> symbols() {
    return symbols.keySet().stream();
  }
}
