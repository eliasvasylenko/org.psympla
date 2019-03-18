package org.psympla.parser.earley;

import java.util.Objects;

import org.psympla.grammar.Rule;

/*
 * TODO abstract over both rules and lexical groups. Perhaps by generating a synthetic rule for each terminal.
 */
//TODO value type & record
public class LR0Item {
  private final Rule rule;
  private final int dotPosition;

  public LR0Item(Rule rule, int dotPosition) {
    this.rule = rule;
    this.dotPosition = dotPosition;
  }

  public Rule rule() {
    return rule;
  }

  public int dotPosition() {
    return dotPosition;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != getClass())
      return false;

    LR0Item that = (LR0Item) obj;

    return Objects.equals(this.rule, that.rule) && (this.dotPosition == that.dotPosition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rule, dotPosition);
  }
}
