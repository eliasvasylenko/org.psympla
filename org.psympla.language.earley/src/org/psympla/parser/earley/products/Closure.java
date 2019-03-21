package org.psympla.parser.earley.products;

import static java.util.Collections.emptySet;
import static org.psympla.parser.earley.products.Nullability.NON_NULLABLE;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.parser.earley.LR0Item;
import org.psympla.parser.index.RuleIndex;
import org.psympla.parser.index.TerminalRule;

public class Closure {
  private final LR0Item item;

  private LR0Item predecessor;
  private Set<LR0Item> predictors;

  private Set<LR0Item> predictions;
  private Nullability nullability;

  private Closure(LR0Item item) {
    this.item = item;

    this.predictors = emptySet();
    this.predecessor = null;

    this.predictions = emptySet();
    this.nullability = NON_NULLABLE;
  }

  public static Stream<Closure> forTerminalRules(RuleIndex<?> ruleIndex) {
    ruleIndex.getTerminalRules().flatMap(Closure::forTerminalRule);
  }

  private static Stream<Closure> forTerminalRule(TerminalRule<?> rule) {
    var prediction = new Closure(new LR0Item(rule, 0));
    return rule.isNullable() ? Stream.of(prediction, prediction.advance()) : Stream.of(prediction);
  }

  public static Stream<Closure> forRules(Grammar grammar) {
    // TODO Auto-generated method stub
    return null;
  }

  public LR0Item item() {
    return item;
  }

  private Closure advance() {
    // TODO Auto-generated method stub
    return null;
  }

  private void predict(Rule rule, LR0Item predictor) {
    predictions
        .computeIfAbsent(new LR0Item(rule, 0), item -> new Prediction(item, ruleIndex))
        .predictFrom(predictor);
  }

  public Optional<LR0Item> prediction(LR0Item item) {
    return Optional.ofNullable(predictions.get(item));
  }

  public Stream<LR0Item> predictions() {
    return predictions.values().stream();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + predictions;
  }
}
