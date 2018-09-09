package org.psympla.lexicon.scanning;

import java.util.stream.Stream;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Sequence;
import org.psympla.symbol.Cell;

/**
 * A scanner accepts a list of characters and outputs a set of {@link Scan
 * scans} matching those characters. Each such scan represents a contiguous
 * sub-sequence of the accepted characters, starting at index 0, and may be
 * evaluated into a sequence of parameters.
 * <p>
 * A scanner is intended to be used in order to define the behavior of a
 * {@link LexicalClass lexical class}, where each scan would be associated with
 * a {@link Lexeme lexeme} of that class.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Scanner<C> {
  Stream<Scan> scan(Sequence<C> characters);

  Sequence<C> print(Cell cell);
}
