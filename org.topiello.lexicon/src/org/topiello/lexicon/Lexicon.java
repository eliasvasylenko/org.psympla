package org.topiello.lexicon;

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
public interface Lexicon<T, C extends TextUnit> {
  Stream<? extends LexicalClass<T, C>> getLexicalClasses();
}
