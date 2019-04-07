package org.topiello.pattern;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.topiello.symbol.LexicalItem;
import org.topiello.symbol.Sequence;

/**
 * 
 * 
 * 
 * 
 * 
 * TODO two different contexts we may use patterns
 * 
 * In one, we need no type-checking and also have no good way to properly type
 * variables, this is where we're defining a rule and defining constraints.
 * 
 * In another we're resolving against instantiations and we need type checking.
 * We don't necessarily know what types are valid for a variable, but we can
 * properly statically check if it satisfies the type we give! Which means
 * symbols extracted are validated as the correct runtime type. This is useful
 * for example in the semantics API, which is the part where we interface
 * between Java and the underlying grammar model, so it makes sense to be the
 * place that the java type system is applied.
 * 
 * 
 * 
 */
public final class Patterns {
  private static final Wildcard WILDCARD = new Wildcard();
  private static final Literal NIL = new Literal(Sequence.empty());

  private Patterns() {}

  public static Pattern cons(Pattern car, Pattern cdr) {
    return new Cons(car, cdr);
  }

  public static Pattern cons(LexicalItem car, Pattern cdr) {
    return new Cons(literal(car), cdr);
  }

  public static Pattern cons(Pattern car, Sequence cdr) {
    return new Cons(car, literal(cdr));
  }

  public static Pattern sequence(Pattern... items) {
    return sequence(Arrays.asList(items));
  }

  public static Pattern sequence(Collection<? extends Pattern> items) {
    return sequence(List.copyOf(items));
  }

  private static Pattern sequence(List<Pattern> items) {
    return items.size() == 0
        ? NIL
        : new Cons(items.get(0), sequence(items.subList(1, items.size())));
  }

  public static Pattern literal(LexicalItem lexicalItem) {
    return new Literal(lexicalItem);
  }

  public static final Pattern wildcard() {
    return WILDCARD;
  }

  public static Variable variable(String name) {
    return new Variable(name);
  }
}
