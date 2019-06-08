package org.topiello.earley;

import java.util.List;

import org.topiello.ast.ItemNode;
import org.topiello.derivation.ParseNode;

public class EarleyItem {
  private static final int INITIAL_PREDICTOR_LIST_SIZE = 8;

  private final ParseNode parseNode = null;

  private final ItemNode lr0Item;

  private final EarleyItem advancedFrom;
  private final List<EarleyItem> predictors; // if we completed from an earlier item, we share
                                             // this

  EarleyItem(ItemNode lr0Item) {
    this.lr0Item = lr0Item;
    this.advancedFrom = null;
    this.predictors = null;
  }

  EarleyItem(ItemNode lr0Item, EarleyItem advancedFrom) {
    this.lr0Item = lr0Item;
    this.advancedFrom = advancedFrom;
    this.predictors = advancedFrom.predictors;
  }

  EarleyItem advance(EarleyItem completed) {
    return new EarleyItem(((ItemNode.Specialized) lr0Item).nextItem().get(), this);
  }

  public ItemNode lr0Item() {
    return lr0Item;
  }
}
