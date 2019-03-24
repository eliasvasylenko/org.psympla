package org.psympla.language.earley;

import java.util.Objects;

import org.psympla.grammar.Rule;

//TODO value type & record
public class EarleyItem {
  private final LR0Item lr0Item;
  private final int inputOrigin;
  private final int inputPosition;

  public EarleyItem(LR0Item lr0Item, int inputOrigin, int inputPosition) {
    this.lr0Item = lr0Item;
    this.inputOrigin = inputOrigin;
    this.inputPosition = inputPosition;
  }

  public Rule rule() {
    return lr0Item.rule();
  }

  public int dotPosition() {
    return lr0Item.dotPosition();
  }

  public LR0Item lr0Item() {
    return lr0Item;
  }

  public int inputOrigin() {
    return inputOrigin;
  }

  public int inputPosition() {
    return inputPosition;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != getClass())
      return false;

    EarleyItem that = (EarleyItem) obj;

    return Objects.equals(this.lr0Item, that.lr0Item) && (this.inputOrigin == that.inputOrigin)
        && (this.inputPosition == that.inputPosition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lr0Item, inputOrigin, inputPosition);
  }
}
