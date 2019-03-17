package org.psympla.parser.earley.products;

import java.util.HashMap;
import java.util.Map;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;

public class ProductPredictionMap {
  private final Map<Product, Predictions> predictions;

  /*
   * TODO this is basically pre-computing the prediction (and scanning/completion
   * over nullables) so we have pre-prepped tables. Since this is the case, it
   * might make sense to simply use the same data types? Though in this case it's
   * a table without an index, and earley items without the index, so maybe not.
   * Also we probably need to keep track of a little extra information to deal
   * with specialization and guard against problematic recursion etc.
   * 
   * TODO I think it might be best to try make a naive direct implementation of
   * the algorithm then revisit the pre-processing, since A) it's an optimization
   * and B) it follows many of the same rules.
   */

  public ProductPredictionMap(Grammar grammar, Lexicon<?> lexicon) {
    predictions = new HashMap<>();

    grammar
        .getRules()
        .flatMap(Product::getProducts)
        .forEach(product -> predictions.computeIfAbsent(product, p -> new Predictions(product)));

    for (var product : predictions.keySet()) {
      System.out.println(product);
      System.out.println(" --- " + predictions.get(product));
      System.out.println();
    }
  }
}
