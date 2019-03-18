package org.psympla.parser.index;

import static java.util.stream.Stream.concat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.psympla.constraint.Match;
import org.psympla.constraint.ValueType;
import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Lexicon;
import org.psympla.pattern.Cons;
import org.psympla.pattern.Literal;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Patterns;
import org.psympla.pattern.Variable;
import org.psympla.symbol.Cell;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;

/**
 * An index to quickly find potentially matching rules for a term, including
 * synthetic terminal rules for {@link LexicalClass lexical classes}.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class RuleIndex<C> {
  // TODO value class
  private static class PatternIndex {
    private final boolean parametric;
    private final Optional<Symbol> symbol;

    public PatternIndex(Pattern pattern) {
      LexicalItem identifier;

      if (pattern.getClass() == Literal.class) {
        var lexicalItem = ((Literal) pattern).lexicalItem();

        this.parametric = lexicalItem.getClass() == Cell.class;
        identifier = parametric ? ((Cell<?, ?>) lexicalItem).car() : lexicalItem;

      } else {
        Pattern identifierPattern;

        this.parametric = pattern.getClass() == Cons.class;
        identifierPattern = parametric ? ((Cons) pattern).car() : pattern;
        identifier = identifierPattern instanceof Literal
            ? ((Literal) identifierPattern).lexicalItem()
            : null;
      }

      this.symbol = identifier instanceof Symbol
          ? Optional.of((Symbol) identifier)
          : Optional.empty();
    }
  }

  private static final Variable LEXEME = Patterns.variable("T");

  private final Map<Symbol, List<Rule>> sequences = new HashMap<>();
  private final Map<Symbol, List<Rule>> singles = new HashMap<>();
  private final Map<Symbol, Rule> terminalRules = new HashMap<>();
  private final Map<Rule, LexicalClass<C, ?>> lexicalClasses = new HashMap<>();

  public RuleIndex(Grammar grammar, Lexicon<C> lexicon) {
    grammar.getRules().forEach(this::addRule);
    lexicon.getLexicalClasses().forEach(this::addLexicalClass);
  }

  private void addLexicalClass(LexicalClass<C, ?> lexicalClass) {
    Rule rule = createTerminalRule(lexicalClass);
    terminalRules.put(lexicalClass.symbol(), rule);
    lexicalClasses.put(rule, lexicalClass);
    addRule(rule);
  }

  private Rule createTerminalRule(LexicalClass<C, ?> lexicalClass) {
    return new Rule(lexicalClass.pattern())
        .withProduct(LEXEME)
        .withConstraint(new ValueType<>(LEXEME, String.class));
  }

  private void addRule(Rule rule) {
    Pattern pattern = rule.pattern();
    var index = new PatternIndex(pattern);

    (index.parametric ? sequences : singles)
        .computeIfAbsent(index.symbol.orElse(null), s -> new ArrayList<>())
        .add(rule);
  }

  public Stream<Rule> getRules(Pattern pattern) {
    var index = new PatternIndex(pattern);

    return index.symbol
        .map(s -> getRules(s, index.parametric))
        .orElseGet(() -> getRules(index.parametric))
        .filter(t -> new Match(pattern, t.pattern()).isValid());
  }

  public Stream<Rule> getTerminalRules() {
    return terminalRules.values().stream();
  }

  public Rule getTerminalRule(Symbol symbol) {
    return terminalRules.get(symbol);
  }

  public Optional<LexicalClass<C, ?>> getLexicalClass(Rule rule) {
    return Optional.ofNullable(lexicalClasses.get(rule));
  }

  public boolean isTerminalRule(Rule rule) {
    return lexicalClasses.containsKey(rule);
  }

  private Stream<Rule> getRules(Symbol symbol, boolean parametric) {
    return concat(
        (parametric ? sequences : singles).get(symbol).stream(),
        Stream.of(sequences.get(null), singles.get(null)).flatMap(List::stream));
  }

  private Stream<Rule> getRules(boolean parametric) {
    return parametric
        ? concat(singles.get(null).stream(), sequences.values().stream().flatMap(List::stream))
        : Stream
            .of(singles.values(), sequences.values())
            .flatMap(Collection::stream)
            .flatMap(List::stream);
  }

  @Override
  public String toString() {
    return singles.toString() + sequences.toString();
  }
}
