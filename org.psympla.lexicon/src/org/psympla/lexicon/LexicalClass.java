package org.psympla.lexicon;

import static org.psympla.pattern.Patterns.cons;
import static org.psympla.pattern.Patterns.wildcard;

import java.util.stream.Stream;

import org.psympla.pattern.Cons;
import org.psympla.pattern.Literal;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;

/*
 * TODO in format do we want to describe a lexical class
 * as e.g.
 * 
 * (operator O) : O ~= "[+-/\*]"
 * 
 * or
 * 
 * (plus-operator O) : O = "+"
 * 
 * But that doesn't describe the relationship between the
 * parameter O and the actual sequence of characters. So
 * when we load these lexical classes into a lexicon, how
 * do we select their implementations?
 * 
 * Maybe we want to construct a lexical class from a
 * library of named (and importable) lexical classes:
 * 
 * operator = regex("+-/\*]")
 * 
 * or
 * 
 * plus-operator = literal("+")
 * 
 * Or maybe it all should be declarative, with relationships
 * to literal text described via constraints:
 * 
 * (operator O) -> O : O ~= "+-/\*]"
 * 
 * or for something which parses an int
 * 
 * (value I) -> S : I = int.parse(S)
 * 
 * or best of both worlds
 * 
 * operator => regex("...")
 * 
 * is shorthand for
 * 
 * (operator S) => S : S = regex("...")
 * 
 * or
 * 
 * (operator S) => S : S ~= "..."
 * 
 * Then regex is just a normal constraint.
 * 
 * But! L is not a String, it's a stream of input characters,
 * and we don't know how many we want to consume or can consume.
 * 
 * So, we need to have a system which identifies a pattern
 * applied to S that can be handled by a particular lexical class
 * and construct an appropriate instance. Must handle doing
 * multiple things to S creating a merged lexical class
 * 
 * (operator U L) => S : U = "'" + S + "'"
 *                     & S ~= "[0-8]+"
 *                     & L = int.parse(S)
 * 
 * Think about how pieces fit together
 * 
 * custom lexeme parser which ties into existing parser and
 * emits business object
 * 
 * semantics which consumes business object
 * 
 * can we communicate through parameters? shouldn't they just
 * be restricted to certain types? carrying semantics is
 * putting the information in the wrong place.
 * 
 * We're already designing towards a notion of importing
 * symbols, how about we can import them from a completely
 * different system. We don't know whether they're terminal
 * or what rules of lexemes can be associated with them until
 * we import them. This creates a natural place to insert a
 * different type of parser.
 * 
 * Maybe we can also override existing symbols in the lexicon
 * or grammar. Maybe we can override a lexical class with a
 * set of grammar rules or vice versa.
 * 
 * Solves the issue of where to delineate external handling of
 * grammar / lexing, but doesn't solve problem of bridging the
 * gap between the grammar and the semantics.
 * 
 * What about supporting e.g. embedding another language in a
 * string literal that might contain escape sequences?
 */
public interface LexicalClass<C, T extends LexicalItem> {
  Symbol symbol();

  Stream<Lexeme<C, T>> scan(Sequence<C> characters);

  Lexeme<C, T> print(Token<T> token);

  default Literal instance(T parameter) {
    return Patterns.literal(symbol().consOnto(parameter));
  }
}
