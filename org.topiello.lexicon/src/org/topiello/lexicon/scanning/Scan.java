package org.topiello.lexicon.scanning;

import java.util.function.Function;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.symbol.LexicalItem;

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