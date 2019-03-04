package org.psympla.parser.earley;

import java.util.Optional;

import org.psympla.grammar.Rule;
import org.psympla.pattern.Pattern;

public class EarleyItem {
  private final Rule rule;
  private final int position;

  public EarleyItem(Rule rule, int position) {
    this.rule = rule;
    this.position = position;
  }

  public Optional<Pattern> nextItem() {
    if (isComplete()) {
      return Optional.empty();
    } else {
      return Optional.of(rule.products().skip(position).findFirst().get());
    }
  }

  public boolean isComplete() {
    return position == rule.products().count();
  }

  public int position() {
    return position;
  }

  public Rule rule() {
    return rule;
  }
}
