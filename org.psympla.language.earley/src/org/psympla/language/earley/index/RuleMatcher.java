package org.psympla.language.earley.index;

import static java.util.Collections.emptyList;
import static java.util.stream.Stream.concat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.psympla.constraint.Match;
import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.LexicalClass;
import org.psympla.pattern.Pattern;
import org.psympla.symbol.Symbol;

/**
 * An index to quickly find potentially matching rules for a term, including
 * {@link TerminalRule synthetic terminal rules} for {@link LexicalClass lexical
 * classes}.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class RuleMatcher<C> {
  private final Map<Symbol, TerminalRule<C>> terminals = new HashMap<>();
  private final Map<Symbol, List<NonterminalRule>> sequences = new HashMap<>();
  private final Map<Symbol, List<NonterminalRule>> singles = new HashMap<>();

  public RuleMatcher(Grammar grammar) {
    grammar.getRules().forEach(this::addRule);
  }

  public RuleMatcher(IndexedLanguage<?> language) {
    grammar.getRules().forEach(this::addRule);
    terminals.getRules().forEach(this::addRule);
  }

  private void addRule(Rule rule) {
    Pattern pattern = rule.pattern();
    var index = new RulePattern(pattern);

    (index.parametric() ? sequences : singles)
        .computeIfAbsent(index.symbol().orElse(null), s -> new ArrayList<>())
        .add(rule);
  }

  public Stream<Rule> getRules() {
    return Stream
        .of(sequences.values(), singles.values())
        .flatMap(Collection::stream)
        .flatMap(Collection::stream);
  }

  public Stream<TerminalRule<C>> getRules(Pattern pattern) {
    var index = new RulePattern(pattern);

    return index.parametric()
        ? index
            .symbol()
            .map(terminalRules::get)
            .flatMap(Optional::ofNullable)
            .map(Stream::of)
            .orElseGet(terminalRuleList::stream)
        : Stream.empty();
  }

  public Stream<Rule> getRules(Pattern pattern) {
    var index = new RulePattern(pattern);

    return index
        .symbol()
        .map(s -> getRules(s, index.parametric()))
        .orElseGet(() -> getRules(index.parametric()))
        .filter(t -> new Match(pattern, t.pattern()).isValid());
  }

  private Stream<Rule> getRules(Symbol symbol, boolean parametric) {
    return concat(
        (parametric ? sequences : singles).getOrDefault(symbol, emptyList()).stream(),
        Stream
            .of(sequences.getOrDefault(null, emptyList()), singles.getOrDefault(null, emptyList()))
            .flatMap(List::stream));
  }

  private Stream<Rule> getRules(boolean parametric) {
    return parametric
        ? concat(
            singles.getOrDefault(null, emptyList()).stream(),
            sequences.values().stream().flatMap(List::stream))
        : Stream
            .of(singles.values(), sequences.values())
            .flatMap(Collection::stream)
            .flatMap(List::stream);
  }

  @Override
  public String toString() {
    return singles.toString() + sequences.toString();
  }

  public int count() {
    return sequences.size() + singles.size();
  }
}
