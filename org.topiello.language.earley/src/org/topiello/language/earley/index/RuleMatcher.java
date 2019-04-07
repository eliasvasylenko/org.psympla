package org.topiello.language.earley.index;

import static java.util.Collections.emptyList;
import static java.util.stream.Stream.concat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.constraint.Match;
import org.topiello.lexicon.LexicalClass;
import org.topiello.pattern.Pattern;
import org.topiello.symbol.Symbol;
import org.topiello.text.TextUnit;

/**
 * An index to quickly find potentially matching rules for a term, including
 * {@link TerminalRule synthetic terminal rules} for {@link LexicalClass lexical
 * classes}.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class RuleMatcher<C extends TextUnit> {
  private final Map<Symbol, TerminalRule<C>> terminals = new HashMap<>();
  private final Map<Symbol, List<NonterminalRule>> sequences = new HashMap<>();
  private final Map<Symbol, List<NonterminalRule>> singles = new HashMap<>();

  public RuleMatcher(IndexedLanguage<C> language) {
    language.nonterminalRules().forEach(this::addRule);
    language.terminalRules().forEach(this::addRule);
  }

  private void addRule(NonterminalRule rule) {
    Pattern pattern = rule.pattern();
    var index = new RulePattern(pattern);

    (index.parametric() ? sequences : singles)
        .computeIfAbsent(index.symbol().orElse(null), s -> new ArrayList<>())
        .add(rule);
  }

  private void addRule(TerminalRule<C> rule) {
    terminals.put(rule.lexicalClass().symbol(), rule);
  }

  public Stream<IndexedRule> getRules(Pattern pattern) {
    return Stream.concat(getNonterminalRules(pattern), getTerminalRules(pattern));
  }

  public Stream<NonterminalRule> getNonterminalRules(Pattern pattern) {
    var index = new RulePattern(pattern);

    return index
        .symbol()
        .map(s -> getNonterminalRules(s, index.parametric()))
        .orElseGet(() -> getNonterminalRules(index.parametric()))
        .filter(t -> new Match(pattern, t.pattern()).isValid());
  }

  private Stream<NonterminalRule> getNonterminalRules(Symbol symbol, boolean parametric) {
    return concat(
        (parametric ? sequences : singles).getOrDefault(symbol, emptyList()).stream(),
        Stream
            .of(sequences.getOrDefault(null, emptyList()), singles.getOrDefault(null, emptyList()))
            .flatMap(List::stream));
  }

  private Stream<NonterminalRule> getNonterminalRules(boolean parametric) {
    return parametric
        ? concat(
            singles.getOrDefault(null, emptyList()).stream(),
            sequences.values().stream().flatMap(List::stream))
        : Stream
            .of(singles.values(), sequences.values())
            .flatMap(Collection::stream)
            .flatMap(List::stream);
  }

  public Stream<TerminalRule<C>> getTerminalRules(Pattern pattern) {
    var index = new RulePattern(pattern);

    return index.parametric()
        ? index
            .symbol()
            .map(terminals::get)
            .flatMap(Optional::ofNullable)
            .map(Stream::of)
            .orElseGet(() -> terminals.values().stream())
        : Stream.empty();
  }

  public int count() {
    return terminals.size() + sequences.size() + singles.size();
  }
}
