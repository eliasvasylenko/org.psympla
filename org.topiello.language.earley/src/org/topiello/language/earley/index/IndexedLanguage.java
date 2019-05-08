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
  private final List<IndexedRule<T>> rules;

  public IndexedLanguage(Grammar<T> grammar, Lexicon<T, C> lexicon) {
    var rules = grammar.getRules().collect(toList());
    this.rules = range(0, rules.size())
        .mapToObj(i -> new IndexedRule<T>(i, this, rules.get(i)))
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

  public IndexedRule<T> rule(int index) {
    return rules.get(index);
  }

  public int ruleCount() {
    return rules.size();
  }

  public Stream<IndexedRule<T>> rules() {
    return rules.stream();
  }

  public RuleSet<T> createRuleSet() {
    return new RuleSet<>(this);
  }
}
