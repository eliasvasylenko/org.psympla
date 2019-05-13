package org.topiello.language.earley.index;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.stream.Stream;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Product;
import org.topiello.lexicon.Lexicon;
import org.topiello.text.TextUnit;

public class IndexedLanguage<T extends Product, C extends TextUnit> {
  private final List<IndexedRule<T>> indexedRules;
  private final List<IndexedTerminal<C>> terminalClasses;

  public IndexedLanguage(Grammar<T> grammar, Lexicon<C> lexicon) {
    var rules = grammar.getRules().collect(toList());
    this.indexedRules = range(0, rules.size())
        .mapToObj(i -> new IndexedRule<>(i, this, rules.get(i)))
        .collect(toList());

    var lexicalClasses = lexicon.getLexicalClasses().collect(toList());
    this.terminalClasses = range(0, lexicalClasses.size())
        .mapToObj(i -> new IndexedTerminal<>(i, this, lexicalClasses.get(i)))
        .collect(toList());

    rules().flatMap(IndexedRule::items).forEach(item -> {
      System.out.println(item.item());
      System.out.println();
    });
  }

  public IndexedRule<T> rule(int index) {
    return indexedRules.get(index);
  }

  public int ruleCount() {
    return indexedRules.size();
  }

  public Stream<IndexedRule<T>> rules() {
    return indexedRules.stream();
  }

  public RuleSet<T> createRuleSet() {
    return new RuleSet<>(this);
  }

  public IndexedTerminal<C> terminal(int index) {
    return terminalClasses.get(index);
  }

  public int terminalCount() {
    return terminalClasses.size();
  }

  public Stream<IndexedTerminal<C>> terminals() {
    return terminalClasses.stream();
  }

  public TerminalSet<C> createTerminalSet() {
    return new TerminalSet<>(this);
  }
}
