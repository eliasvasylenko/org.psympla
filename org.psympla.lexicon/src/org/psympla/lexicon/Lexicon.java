package org.psympla.lexicon;

import static java.util.Collections.singleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
  private final List<LexicalClass<C>> lexicalClasses;

  protected Lexicon(Collection<? extends LexicalClass<C>> lexicalClasses) {
    this.lexicalClasses = new ArrayList<>(lexicalClasses);
  }

  private Lexicon(List<LexicalClass<C>> lexicalClasses) {
    this.lexicalClasses = lexicalClasses;
  }

  public Stream<Lexeme<C>> scan(Symbol symbol, Sequence<C> characters) {
    return lexicalClasses
        .stream()
        .filter(lexicalClass -> lexicalClass.symbol().equals(symbol))
        .flatMap(lexicalClass -> lexicalClass.scan(characters));
  }

  public Lexicon<C> withLexicalClass(LexicalClass<C> lexicalClass) {
    return withLexicalClasses(singleton(lexicalClass));
  }

  public Lexicon<C> withLexicalClasses(Collection<? extends LexicalClass<C>> lexicalClasses) {
    var newLexicalClasses = new ArrayList<LexicalClass<C>>(
        this.lexicalClasses.size() + lexicalClasses.size());
    newLexicalClasses.addAll(this.lexicalClasses);
    lexicalClasses.forEach(newLexicalClasses::add);
    return new Lexicon<>(newLexicalClasses);
  }
}
