package org.psympla.language.earley;

import java.util.ArrayList;
import java.util.List;

import org.psympla.grammar.Rule;

public class EarleyItemNode {
  private static final int INITIAL_PREDICTOR_LIST_SIZE = 8;

  private final EarleyItem item;
  private final EarleyItemNode advancedFrom;
  private final List<EarleyItemNode> predictors; // if we completed from an earlier item, we share
                                                 // this

  EarleyItemNode(EarleyItem item) {
    this(item, null);
  }

  EarleyItemNode(EarleyItem item, EarleyItemNode advancedFrom) {
    this.item = item;
    this.advancedFrom = advancedFrom;
    this.predictors = new ArrayList<>(INITIAL_PREDICTOR_LIST_SIZE);
  }

  EarleyItemNode advance(EarleyItemNode completed) {
    return new EarleyItemNode(
        new EarleyItem(item.lr0Item(), item.inputOrigin(), completed.item().inputPosition()),
        this);
  }

  public EarleyItem item() {
    return item;
  }

  public Rule rule() {
    return item.rule();
  }

  public int dotPosition() {
    return item.dotPosition();
  }

  public LR0Item lr0Item() {
    return item.lr0Item();
  }

  public int inputOrigin() {
    return item.inputOrigin();
  }

  public int inputPosition() {
    return item.inputPosition();
  }
}
