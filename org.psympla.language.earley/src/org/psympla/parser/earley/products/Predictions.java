package org.psympla.parser.earley.products;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.psympla.grammar.Rule;
import org.psympla.parser.earley.LR0Item;
import org.psympla.parser.index.RuleIndex;

public class Predictions<C> {
  private final Product product;
  private final Map<LR0Item, Prediction> predictions;

  public Predictions(Product product, RuleIndex<C> ruleIndex) {
    this.product = product;
    this.predictions = new HashMap<>();
    ruleIndex.getRules(product.pattern()).forEach(this::addPrediction);
  }

  private void addPrediction(Rule rule) {
    var item = new LR0Item(rule, 0);
    predictions.put(item, new Prediction(item));
  }

  public Product product() {
    return product;
  }

  public Optional<Prediction> prediction(LR0Item item) {
    return Optional.ofNullable(predictions.get(item));
  }

  public Stream<Prediction> predictions() {
    return predictions.values().stream();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + predictions;
  }
}
