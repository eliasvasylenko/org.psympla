package org.psympla.lexicon;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.psympla.symbol.Symbol;

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
  private static final Lexicon<Object> EMPTY = new Lexicon<>(emptyList());

  @SuppressWarnings("unchecked")
  public static final <C> Lexicon<C> empty() {
    return (Lexicon<C>) EMPTY;
  }

  private final Map<Symbol, LexicalClass<C, ?>> lexicalClasses;

  protected Lexicon(Collection<? extends LexicalClass<C, ?>> lexicalClasses) {
    this.lexicalClasses = lexicalClasses
        .stream()
        .collect(toMap(LexicalClass::symbol, Function.identity()));
  }

  public Lexicon<C> withLexicalClass(LexicalClass<C, ?> lexicalClass) {
    return withLexicalClasses(singleton(lexicalClass));
  }

  public Lexicon<C> withLexicalClasses(Collection<? extends LexicalClass<C, ?>> lexicalClasses) {
    var newLexicalClasses = new ArrayList<LexicalClass<C, ?>>(
        this.lexicalClasses.size() + lexicalClasses.size());
    newLexicalClasses.addAll(this.lexicalClasses.values());
    newLexicalClasses.addAll(lexicalClasses);
    return new Lexicon<>(newLexicalClasses);
  }

  public Stream<Symbol> getSymbols() {
    return lexicalClasses.keySet().stream();
  }

  public Stream<LexicalClass<C, ?>> getLexicalClasses() {
    return lexicalClasses.values().stream();
  }

  public LexicalClass<C, ?> getLexicalClass(Symbol symbol) {
    return lexicalClasses.get(symbol);
  }
}
