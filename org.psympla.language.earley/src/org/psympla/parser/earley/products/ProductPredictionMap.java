package org.psympla.parser.earley.products;

import java.util.HashMap;
import java.util.Map;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;
import org.psympla.parser.index.GrammaticIndex;
import org.psympla.parser.index.LexicalIndex;

public class ProductPredictionMap<C> {
  private final GrammaticIndex grammaticIndex;
  private final LexicalIndex<C> lexicalIndex;
  private final Map<Product, Predictions> predictions;

  /*
   * TODO this is basically pre-computing the prediction (and scanning/completion
   * over nullables) so we have pre-prepped tables. Since this is the case, it
   * might make sense to simply use the same data types? Though in this case it's
   * a table without an index, and earley items without the index, so maybe not.
   * Also we probably need to keep track of a little extra information to deal
   * with specialization and guard against problematic recursion etc.
   */

  public ProductPredictionMap(Grammar grammar, Lexicon<C> lexicon) {
    this.grammaticIndex = new GrammaticIndex(grammar);
    this.lexicalIndex = new LexicalIndex<>(lexicon);
    this.predictions = new HashMap<>();

    grammar
        .getRules()
        .flatMap(Product::getProducts)
        .forEach(product -> predictions.computeIfAbsent(product, p -> new Predictions(product)));

    System.out.println(grammaticIndex);
    System.out.println(lexicalIndex);
    for (var product : predictions.keySet()) {
      System.out.println(product);
      System.out.println(" --- " + predictions.get(product));
      System.out.println();
    }
  }
}
