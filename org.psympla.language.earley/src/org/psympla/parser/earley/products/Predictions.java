package org.psympla.parser.earley.products;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.psympla.parser.earley.LR0Item;

public class Predictions {
  private final Map<LR0Item, Prediction> predictions;

  public Predictions(Product product) {
    this.predictions = new HashMap<>();
  }

  public Optional<Prediction> prediction(LR0Item item) {
    return Optional.ofNullable(predictions.get(item));
  }

  public Stream<Prediction> predictions() {
    return predictions.values().stream();
  }

  @Override
  public String toString() {
    return predictions.toString();
  }
}
