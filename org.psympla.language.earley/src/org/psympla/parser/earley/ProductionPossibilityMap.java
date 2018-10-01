package org.psympla.parser.earley;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Lexicon;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Pattern;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Variable;

public class ProductionPossibilityMap {
  public static class LhsItem {
    private final Symbol symbol;
    private final Pattern<? extends LexicalItem<?>> pattern;
    private final Map<Variable<?>, Set<Symbol>> mentionedSymbols = new HashMap<>();
    private final List<RhsItem> outgoingEdges = new ArrayList<>();
    private final List<RhsItem> incomingEdges = new ArrayList<>();

    public LhsItem(Symbol symbol, Pattern<? extends LexicalItem<?>> pattern) {
      this.symbol = symbol;
      this.pattern = pattern;
    }

    public boolean addMentionedSymbols(Variable<?> variable, Collection<? extends Symbol> symbols) {
      return mentionedSymbols.computeIfAbsent(variable, v -> new HashSet<>()).addAll(symbols);
    }
  }

  public static class RhsItem {
    private final Rule rule;
    private final int productionItem;

    public RhsItem(Rule rule, int productionItem) {
      this.rule = rule;
      this.productionItem = productionItem;
    }
  }

  private final Map<Symbol, Set<LhsItem>> vertices = new HashMap<>();

  public ProductionPossibilityMap(Grammar grammar, Lexicon<?> lexicon) {
    var indexedGrammar = new IndexedGrammar(grammar);
    concat(
        indexedGrammar.symbols(),
        lexicon.getLexicalClasses().map(LexicalClass::symbol).distinct())
            .map(s -> new LhsItem(s, Nil.instance()))
            .collect(toSet())
            .forEach(this::addVertex);

    System.out.println(vertices);
  }

  void addVertex(LhsItem vertex) {
    vertices.add(vertex);
  }
}
