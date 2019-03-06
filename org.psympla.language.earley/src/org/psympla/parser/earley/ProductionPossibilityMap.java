package org.psympla.parser.earley;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

import java.util.List;
import java.util.Set;

import org.psympla.constraint.Constraint;
import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Lexicon;
import org.psympla.parser.earley.ProductionPossibilityMap.LhsItem;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.Sequence;

import sun.security.provider.certpath.Vertex;

public class ProductionPossibilityMap {
  public static class CompiledRule {
    private final Rule rule;
    private final List<CompiledProduct> products;

    public CompiledRule(Rule rule) {
      this.rule = rule;
    }
  }

  public class CompiledProduct {
    private final Pattern pattern;
    private final Set<Constraint> constraints;
    private final Set<Prediction> predictions;

  }

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

  public class Prediction {
    private final CompiledRule rule;
    private final int dot;
  }

  public ProductionPossibilityMap(Grammar grammar, Lexicon<?> lexicon) {
    var indexedGrammar = new IndexedGrammar(grammar);
    concat(
        indexedGrammar.indices(),
        lexicon.getLexicalClasses().map(LexicalClass::symbol).distinct())
            .map(s -> new Vertex(s, Patterns.literal(Sequence.empty())))
            .collect(toSet())
            .forEach(this::addVertex);

    System.out.println(vertices);
  }

  void addVertex(LhsItem vertex) {
    // vertices.add(vertex);
  }
}
