package org.psympla.language.earley;

import java.util.Objects;

import org.psympla.grammar.Rule;
import org.psympla.language.earley.index.IndexedRule;

//TODO value type & record
public class LR0Item {
  private final IndexedRule rule;
  private final int dotPosition;

  public LR0Item(IndexedRule rule, int dotPosition) {
    this.rule = rule;
    this.dotPosition = dotPosition;
  }

  public Rule rule() {
    return rule;
  }

  public int dotPosition() {
    return dotPosition;
  }

  public boolean isComplete() {
    return rule.length() == dotPosition;
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
