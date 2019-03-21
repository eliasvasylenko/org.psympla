package org.psympla.parser.earley.products;

import java.util.HashMap;
import java.util.Map;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;
import org.psympla.parser.earley.LR0Item;
import org.psympla.parser.index.RuleIndex;

public class ProductionClosures {
  private final RuleIndex<?> ruleIndex;
  private final Map<LR0Item, Closure> closures;

  public ProductionClosures(Grammar grammar, Lexicon<?> lexicon) {
    this.ruleIndex = new RuleIndex<>(grammar, lexicon);

    this.closures = new HashMap<>();
    Closure
        .forTerminalRules(ruleIndex)
        .forEach(prediction -> closures.put(prediction.item(), prediction));
    Closure.forRules(grammar).forEach(prediction -> closures.put(prediction.item(), prediction));

    System.out.println(ruleIndex);
    System.out.println();
    for (var closure : closures.values()) {
      System.out.println(closure);
      System.out.println();
    }
  }
}
