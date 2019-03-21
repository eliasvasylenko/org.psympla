package org.psympla.parser.earley.products;

import static java.util.Collections.emptySet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.psympla.grammar.Rule;
import org.psympla.parser.earley.LR0Item;
import org.psympla.parser.index.RuleIndex;
import org.psympla.parser.index.TerminalRule;

public class Predictions {

  private final LR0Item item;

  private Prediction predecessor;
  private Set<Prediction> predictors;

  private Map<LR0Item, Predictions> predictions;
  private boolean nullable;

  private Predictions(LR0Item item) {

    this.item = item;

    this.predictors = emptySet();
    this.predecessor = null;

    this.predictions = new HashMap<>();
    this.nullable = false;

    ruleIndex.getRules(product.pattern()).forEach(rule -> predict(rule, null));
  }

  public static Stream<Predictions> forTerminalRules(RuleIndex<?> ruleIndex) {
    ruleIndex.getTerminalRules().flatMap(Predictions::forTerminalRule);
  }

  private static Stream<Predictions> forTerminalRule(TerminalRule<?> rule) {
    var prediction = new Predictions(new LR0Item(rule, 0));
    return rule.isNullable() ? Stream.of(prediction, prediction.advance()) : Stream.of(prediction);
  }

  public LR0Item item() {
    return item;
  }

  private Predictions advance() {
    // TODO Auto-generated method stub
    return null;
  }

  private void predict(Rule rule, Prediction predictor) {
    predictions
        .computeIfAbsent(new LR0Item(rule, 0), item -> new Prediction(item, ruleIndex))
        .predictFrom(predictor);
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
