package org.psympla.parser.earley.products;

import java.util.ArrayList;
import java.util.List;

import org.psympla.parser.earley.LR0Item;

public class Prediction {
  private final LR0Item item;
  private final List<Prediction> predictors;

  public Prediction(LR0Item item) {
    this.item = item;
    this.predictors = new ArrayList<>();
  }

  public LR0Item item() {
    return item;
  }

  public List<Prediction> predictors() {
    return predictors;
  }
}
