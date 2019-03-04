package org.psympla.parser.earley;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Lexicon;
import org.psympla.parser.earley.ProductionPossibilityMap.LhsItem;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.Sequence;

public class ProductionPossibilityMap {
  public static class Vertex {
    private final Rule rule;

    public Vertex(Rule rule) {
      this.rule = rule;
    }
  }

  public static class Edge {
    private final Vertex head;
    private final Vertex tail;
    private final int productIndex;

    public Edge(Vertex head, Vertex tail, int productIndex) {
      this.head = head;
      this.tail = tail;
      this.productIndex = productIndex;
    }
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
