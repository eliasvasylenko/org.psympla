package org.psympla.parser.earley;

import java.util.Optional;

import org.psympla.constraint.Match;
import org.psympla.grammar.Rule;
import org.psympla.pattern.Substitution;
import org.psympla.symbol.LexicalItem;

public class EarleyItem {
  private final Rule rule;
  private final int position;
  private final Substitution instantiations;

  public EarleyItem(Rule rule, int position, Substitution instantiations) {
    this.rule = rule;
    this.position = position;
    this.instantiations = instantiations;
  }

  public Optional<Match<? extends LexicalItem>> nextItem() {
    if (isComplete()) {
      return Optional.empty();
    } else {
      return Optional.of(rule.production().item(position));
    }
  }

  public boolean isComplete() {
    return position == rule.production().length();
  }

  public int position() {
    return position;
  }

  public Rule rule() {
    return rule;
  }

  public Substitution instantiations() {
    return instantiations;
  }
}
