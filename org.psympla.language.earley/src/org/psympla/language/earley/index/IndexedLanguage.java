package org.psympla.language.earley.index;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.List;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;

public class IndexedLanguage<C> {
  private final List<NonterminalRule> nonterminalRules;
  private final List<TerminalRule<C>> terminalRules = new ArrayList<>();

  public IndexedLanguage(Grammar grammar, Lexicon<?> lexicon) {
    var rules = grammar.getRules().collect(toList());
    nonterminalRules = range(0, rules.size())
        .mapToObj(i -> new NonterminalRule(i, rules.get(i)))
        .collect(toList());

    var lexicalClasses = lexicon.getLexicalClasses().collect(toList());
    terminalRules = range(0, lexicalClasses.size())
        .map(i -> new TerminalRule<>(i, null, lexicalClasses));

    System.out.println(ruleIndex);
    System.out.println();
    for (var closure : closures.values()) {
      System.out.println(closure);
      System.out.println();
    }
  }

  public NonterminalRule getNonterminalRule(int index) {
    return nonterminalRules.get(index);
  }

  public TerminalRule<C> getTerminalRule(int index) {
    return terminalRules.get(index);
  }

  public int getNonterminalRuleCount() {
    return nonterminalRules.size();
  }

  public int getTerminalRuleCount() {
    return terminalRules.size();
  }
}
