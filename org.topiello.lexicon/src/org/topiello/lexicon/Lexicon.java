package org.topiello.lexicon;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.topiello.text.TextUnit;

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
public class Lexicon<T, C extends TextUnit> {
  private static final Lexicon<?, ?> EMPTY = new Lexicon<>(emptyList());

  @SuppressWarnings("unchecked")
  public static final <T, C extends TextUnit> Lexicon<T, C> empty() {
    return (Lexicon<T, C>) EMPTY;
  }

  private final Set<LexicalClass<T, C>> lexicalClasses;

  protected Lexicon(Collection<? extends LexicalClass<T, C>> lexicalClasses) {
    this.lexicalClasses = Set.copyOf(lexicalClasses);
  }

  public Lexicon<T, C> withLexicalClass(LexicalClass<T, C> lexicalClass) {
    return withLexicalClasses(singleton(lexicalClass));
  }

  public Lexicon<T, C> withLexicalClasses(Collection<? extends LexicalClass<T, C>> lexicalClasses) {
    var newLexicalClasses = new HashSet<LexicalClass<T, C>>(this.lexicalClasses);
    newLexicalClasses.addAll(lexicalClasses);
    return new Lexicon<>(newLexicalClasses);
  }

  public Stream<LexicalClass<T, C>> getLexicalClasses() {
    return lexicalClasses.stream();
  }
}
