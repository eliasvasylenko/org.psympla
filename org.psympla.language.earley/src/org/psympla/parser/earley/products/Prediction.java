package org.psympla.parser.earley.products;

import static java.util.Collections.emptySet;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.psympla.lexicon.Characters;
import org.psympla.parser.earley.LR0Item;
import org.psympla.parser.index.RuleIndex;

public class Prediction {
  private final LR0Item item;
  private Set<Prediction> predictors;
  private Prediction predecessor;

  public Prediction(LR0Item item, RuleIndex<?> ruleIndex) {
    this.item = item;
    this.predictors = emptySet();
    this.predecessor = null;

    ruleIndex.getLexicalClass(item.rule()).ifPresentOrElse(lexicalClass -> {
      boolean nullable = lexicalClass.scan(Characters.empty()).findAny().isPresent();
      if (nullable) {
        predictions(new LR0Item(rule, 1)).proceedFrom(predictions);

      }
    }, () -> {

    });
  }

  public LR0Item item() {
    return item;
  }

  public Stream<Prediction> predictors() {
    return predictors.stream();
  }

  boolean predictFrom(Prediction predictor) {
    if (predictors.isEmpty()) {
      predictors = new HashSet<>();
    }
    return predictors.add(predictor);
  }

  public Stream<Prediction> predecessors() {
    return predecessors.stream();
  }

  boolean proceedFrom(Prediction predecessor) {
    predictors = predecessor.predictors;

    if (predecessors.isEmpty()) {
      predecessors = new HashSet<>();
    }
    return predecessors.add(predecessor);
  }
}
