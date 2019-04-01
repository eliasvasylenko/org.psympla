package org.psympla.language.earley.index;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.stream.Stream;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;
import org.psympla.text.TextUnit;

public class IndexedLanguage<C extends TextUnit> {
  private final List<NonterminalRule> nonterminalRules;
  private final List<TerminalRule<C>> terminalRules;

  public IndexedLanguage(Grammar grammar, Lexicon<C> lexicon) {
    var rules = grammar.getRules().collect(toList());
    nonterminalRules = range(0, rules.size())
        .mapToObj(i -> new NonterminalRule(i, this, rules.get(i)))
        .collect(toList());

    var lexicalClasses = lexicon.getLexicalClasses().collect(toList());
    terminalRules = range(0, lexicalClasses.size())
        .mapToObj(i -> new TerminalRule<C>(i, this, lexicalClasses.get(i)))
        .collect(toList());

    rules().flatMap(IndexedRule::items).forEach(item -> {
      System.out.println(item.item());
      System.out.println();
    });
  }

  public NonterminalRule nonterminalRule(int index) {
    return nonterminalRules.get(index);
  }

  public TerminalRule<C> terminalRule(int index) {
    return terminalRules.get(index);
  }

  public int nonterminalRuleCount() {
    return nonterminalRules.size();
  }

  public int terminalRuleCount() {
    return terminalRules.size();
  }

  public Stream<NonterminalRule> nonterminalRules() {
    return nonterminalRules.stream();
  }

  public Stream<TerminalRule<C>> terminalRules() {
    return terminalRules.stream();
  }

  public Stream<IndexedRule> rules() {
    return Stream.concat(nonterminalRules(), terminalRules());
  }

  public TerminalRuleSet<?> createTerminalRuleSet() {
    return new TerminalRuleSet<>(this);
  }

  public NonterminalRuleSet createNonterminalRuleSet() {
    return new NonterminalRuleSet(this);
  }
}
