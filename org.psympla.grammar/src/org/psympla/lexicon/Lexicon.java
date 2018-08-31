package org.psympla.lexicon;

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
public interface Lexicon<C> {
  Stream<Token<C>> scan(Symbol symbol, List<C> characters);

  Stream<Token<C>> select(Collection<? extends Token<C>> choices);
}
