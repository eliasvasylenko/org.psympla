package org.topiello.language.earley.index;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.stream.Stream;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Rule;
import org.topiello.lexicon.Lexicon;
import org.topiello.text.TextUnit;

public class IndexedLanguage<T extends Rule<?>, C extends TextUnit> {
  private final List<NonterminalRule<T>> nonterminalRules;
  private final List<TerminalRule<T, C>> terminalRules;

  public IndexedLanguage(Grammar<T> grammar, Lexicon<T, C> lexicon) {
    var rules = grammar.getRules().collect(toList());
    nonterminalRules = range(0, rules.size())
        .mapToObj(i -> new NonterminalRule<>(i, this, rules.get(i)))
        .collect(toList());

    var lexicalClasses = lexicon.getLexicalClasses().collect(toList());
    terminalRules = range(0, lexicalClasses.size())
        .mapToObj(i -> new TerminalRule<T, C>(i, this, lexicalClasses.get(i)))
        .collect(toList());

    rules().flatMap(IndexedRule::items).forEach(item -> {
      System.out.println(item.item());
      System.out.println();
    });
  }

  public NonterminalRule<T> nonterminalRule(int index) {
    return nonterminalRules.get(index);
  }

  public TerminalRule<T, C> terminalRule(int index) {
    return terminalRules.get(index);
  }

  public int nonterminalRuleCount() {
    return nonterminalRules.size();
  }

  public int terminalRuleCount() {
    return terminalRules.size();
  }

  public Stream<NonterminalRule<T>> nonterminalRules() {
    return nonterminalRules.stream();
  }

  public Stream<TerminalRule<T, C>> terminalRules() {
    return terminalRules.stream();
  }

  public Stream<IndexedRule<T>> rules() {
    return Stream.concat(nonterminalRules(), terminalRules());
  }

  public TerminalRuleSet<T, C> createTerminalRuleSet() {
    return new TerminalRuleSet<>(this);
  }

  public NonterminalRuleSet<T> createNonterminalRuleSet() {
    return new NonterminalRuleSet<>(this);
  }
}
