package org.psympla.lexicon.scanning;

import java.util.function.Function;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.symbol.LexicalItem;

/**
 * A scan is the unit of output of a {@link Scanner}, which defines the behavior
 * of a {@link LexicalClass}. In particular, each scan instantiates a
 * {@link Lexeme} which belongs to that class.
 * <p>
 * In turn a lexeme may be {@link Lexeme#evaluate() evaluated} to a
 * {@link Token}.
 * 
 * @author Elias N Vasylenko
 */
public interface Scan<T extends LexicalItem> {
  int length();

  T evaluate();

  static <T extends LexicalItem> Scan<T> forParameter(int length, T parameter) {
    return new Scan<>() {
      @Override
      public int length() {
        return length;
      }

      @Override
      public T evaluate() {
        return parameter;
      }
    };
  }

  static <T extends LexicalItem> Scan<T> forEvaluation(int length, Function<Integer, T> parameter) {
    return new Scan<>() {
      @Override
      public int length() {
        return length;
      }

      @Override
      public T evaluate() {
        return parameter.apply(length);
      }
    };
  }
}
