package org.topiello.language.earley;

import java.util.ArrayList;
import java.util.List;

import org.topiello.language.earley.index.IndexedRule;
import org.topiello.language.earley.index.LR0Item;

public class EarleyItem {
  private static final int INITIAL_PREDICTOR_LIST_SIZE = 8;

  private final LR0Item lr0Item;
  private final EarleyItem advancedFrom;
  private final List<EarleyItem> predictors; // if we completed from an earlier item, we share
                                             // this

  EarleyItem(LR0Item lr0Item) {
    this(lr0Item, null);
  }

  EarleyItem(LR0Item lr0Item, EarleyItem advancedFrom) {
    this.lr0Item = lr0Item;
    this.advancedFrom = advancedFrom;
    this.predictors = new ArrayList<>(INITIAL_PREDICTOR_LIST_SIZE);
  }

  EarleyItem advance(EarleyItem completed) {
    return new EarleyItem(lr0Item.nextProduct().get().index(), this);
  }

  public LR0Item lr0Item1() {
    return lr0Item;
  }

  public IndexedRule<?> rule() {
    return lr0Item.rule();
  }

  public int dotPosition() {
    return lr0Item.dotPosition();
  }
}
