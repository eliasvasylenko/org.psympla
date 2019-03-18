package org.psympla.parser.earley.products;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.psympla.parser.earley.LR0Item;
import org.psympla.parser.index.LexicalIndex;

public class Predictions<C> {
  private final Map<LR0Item, Prediction> predictions;

  public Predictions(Product product, LexicalIndex<C> lexicalIndex) {
    this.predictions = new HashMap<>();
    lexicalIndex.getLexicalGroups(product.pattern()).collect(arg0);
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
