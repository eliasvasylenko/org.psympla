package org.psympla.parser.earley;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;

public class IndexedGrammar {
  private final Grammar grammar;
  /**
   * TODO All rule patterns that are not sequences must be specialized. All rule
   * patterns that are sequences must have their left-most component specialized.
   */
  private final Map<Index, IndexedRules> symbols = new HashMap<>();

  public IndexedGrammar(Grammar grammar) {
    this.grammar = grammar;

    grammar.getRules().forEach(this::addRule);
  }

  private void addRule(Rule rule) {
    symbols.computeIfAbsent(new Index(rule.pattern()), IndexedRules::new).addRule(rule);
  }

  public IndexedRules getRules(Index index) {
    var indexed = symbols.get(index);
    return indexed == null ? new IndexedRules(index) : indexed;
  }

  public Stream<Index> indices() {
    return symbols.keySet().stream();
  }
}
