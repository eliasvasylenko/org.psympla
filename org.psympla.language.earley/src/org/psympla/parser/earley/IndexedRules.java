package org.psympla.parser.earley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.psympla.grammar.Rule;

public class IndexedRules {
  private final Index index;
  private final List<Rule> rules = new ArrayList<>();

  IndexedRules(Index index) {
    this.index = index;
  }

  void addRule(Rule rule) {
    rules.add(rule);
  }

  public Index getIndex() {
    return index;
  }

  public List<Rule> getRules() {
    return Collections.unmodifiableList(rules);
  }
}
