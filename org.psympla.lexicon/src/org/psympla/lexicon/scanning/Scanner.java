package org.psympla.lexicon.scanning;

import java.util.stream.Stream;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.symbol.Sequence;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

/**
 * A scanner accepts a list of characters and outputs a set of {@link Scan
 * scans} matching those characters. Each such scan represents a contiguous
 * sub-sequence of the accepted characters, starting at index 0, and may be
 * evaluated into a parameter.
 * <p>
 * A scanner is intended to be used in order to define the behavior of a
 * {@link LexicalClass lexical class}, where each scan would be associated with
 * a {@link Lexeme lexeme} of that class.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public interface Scanner<C extends TextUnit, T extends Sequence> {
  Stream<Scan<T>> scan(Text<C> characters);
}
