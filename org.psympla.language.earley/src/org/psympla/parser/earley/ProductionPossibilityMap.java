package org.psympla.parser.earley;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;

public class ProductionPossibilityMap {
  public static class Vertex {
    private final Symbol symbol;
    private final Pattern<? extends LexicalItem<?>> pattern;
    private final Set<Pattern<? extends LexicalItem<?>>> possibleParameterizations = new HashSet<>();
    private final List<Edge> outgoingEdges = new ArrayList<>();
    private final List<Edge> incomingEdges = new ArrayList<>();

    public Vertex(Symbol symbol, Pattern<? extends LexicalItem<?>> pattern) {
      this.symbol = symbol;
      this.pattern = pattern;
    }

    public boolean addPossibleParameterization(Pattern<? extends LexicalItem<?>> parameterization) {
      return possibleParameterizations.add(parameterization);
    }
  }

  public static class Edge {
    private final Rule rule;
    private final int productionItem;

    public Edge(Rule rule, int productionItem) {
      this.rule = rule;
      this.productionItem = productionItem;
    }
  }

  public ProductionPossibilityMap(Grammar grammar) {
    // TODO Auto-generated constructor stub
  }
}
