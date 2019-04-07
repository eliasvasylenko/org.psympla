package org.topiello.lexicon;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.constraint.Scope;
import org.topiello.pattern.Pattern;
import org.topiello.pattern.Patterns;
import org.topiello.symbol.Sequence;
import org.topiello.symbol.Symbol;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

/*
 * The c
 */
public interface LexicalClass<C extends TextUnit, T extends Sequence> {
  Symbol symbol();

  Pattern parameter();

  default Pattern pattern() {
    return Patterns.cons(symbol(), parameter());
  }

  Stream<Lexeme<C, T>> scan(Text<C> characters);

  Optional<Lexeme<C, T>> print(Token<T> token);

  default Pattern instance(T parameter) {
    return Patterns.literal(symbol().consOnto(parameter));
  }

  Scope scope();
}
