package org.psympla.lexicon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.grammar.Symbol;

/**
 * A lexicon defines which tokens exist over a given character set.
 * <p>
 * Given a terminal symbol and a sequence of characters, it can determine which
 * tokens of that symbol can be recognized.
 * <p>
 * The tokens recognized may be parameterized.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class Lexicon<C> {
  private final List<LexicalClass<C>> scanners;

  protected Lexicon(Collection<? extends LexicalClass<C>> scanners) {
    this.scanners = new ArrayList<>(scanners);
  }

  private Lexicon(List<LexicalClass<C>> scanners) {
    this.scanners = scanners;
  }

  public Stream<Lexeme<C>> scan(Symbol symbol, List<C> characters) {
    return scanners
        .stream()
        .filter(scanner -> scanner.symbol().equals(symbol))
        .map(scanner -> scanner.scan(characters));
  }

  public Lexicon<C> withLexicalClass(LexicalClass<C> scanner) {
    return withLexicalClass(scanner);
  }

  @SafeVarargs
  public final Lexicon<C> withLexicalClass(LexicalClass<C>... tokens) {
    return withLexicalClass(Arrays.asList(tokens));
  }

  public Lexicon<C> withLexicalClass(Collection<? extends LexicalClass<C>> tokens) {
    var newTokens = new ArrayList<LexicalClass<C>>(this.scanners.size() + tokens.size());
    newTokens.addAll(this.scanners);
    tokens.forEach(newTokens::add);
    return new Lexicon<>(newTokens);
  }
}
