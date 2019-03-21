package org.psympla.parser.earley.products;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.Lexicon;
import org.psympla.parser.earley.LR0Item;
import org.psympla.parser.index.RuleIndex;

public class ProductPredictionMap {
  private final RuleIndex<?> ruleIndex;
  private final Map<LR0Item, Predictions> predictions;

  public ProductPredictionMap(Grammar grammar, Lexicon<?> lexicon) {
    this.ruleIndex = new RuleIndex<>(grammar, lexicon);

    this.predictions = new HashMap<>();
    Predictions
        .forTerminalRules(ruleIndex)
        .forEach(prediction -> predictions.put(prediction.item(), prediction));
    grammar
        .getRules()
        .flatMap(this::getLR0Items)
        .forEach(
            product -> productPredictions
                .computeIfAbsent(product, p -> new Predictions<>(product, ruleIndex)));

    this.predictions = new HashMap<>();

    System.out.println(ruleIndex);
    System.out.println();
    for (var product : productPredictions.keySet()) {
      System.out.println(product);
      System.out.println(" --- " + productPredictions.get(product));
      System.out.println();
    }
  }

  private Stream<LR0Item> getLR0Items(Rule rule) {

  }
}
