package org.psympla.lexicon.scanning;

import java.util.function.Supplier;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.symbol.SymbolicExpression;

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
public interface Scan {
  int length();

  SymbolicExpression evaluate();

  static Scan forParameters(int length, SymbolicExpression parameters) {
    return new Scan() {
      @Override
      public int length() {
        return length;
      }

      @Override
      public SymbolicExpression evaluate() {
        return parameters;
      }
    };
  }

  static Scan forEvaluation(int length, Supplier<SymbolicExpression> parameters) {
    return new Scan() {
      @Override
      public int length() {
        return length;
      }

      @Override
      public SymbolicExpression evaluate() {
        return parameters.get();
      }
    };
  }
}
