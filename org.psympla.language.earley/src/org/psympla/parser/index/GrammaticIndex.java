package org.psympla.parser.index;

import java.util.stream.Stream;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.pattern.Pattern;

public class GrammaticIndex extends Index<Rule> {
  private final Grammar grammar;

  public GrammaticIndex(Grammar grammar) {
    super(grammar::getRules, Rule::pattern);
    this.grammar = grammar;
  }

  public Grammar getGrammar() {
    return grammar;
  }

  public Stream<Rule> getRules(Pattern pattern) {
    return getItems(pattern);
  }
}
