package org.psympla.lexicon;

import java.util.stream.Stream;

import org.psympla.constraint.Scope;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;

/*
 * The c
 */
public interface LexicalClass<C, T extends Sequence> {
  Symbol symbol();

  Pattern parameter();

  default Pattern pattern() {
    return Patterns.cons(symbol(), parameter());
  }

  Stream<Lexeme<C, T>> scan(Characters<C> characters);

  Lexeme<C, T> print(Token<T> token);

  default Pattern instance(T parameter) {
    return Patterns.literal(symbol().consOnto(parameter));
  }

  Scope scope();
}
