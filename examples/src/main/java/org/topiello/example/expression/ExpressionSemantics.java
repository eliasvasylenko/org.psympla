package org.topiello.example.expression;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.topiello.example.expression.ExpressionLexicon.ADD;
import static org.topiello.example.expression.ExpressionLexicon.DIVIDE;
import static org.topiello.example.expression.ExpressionLexicon.MULTIPLY;
import static org.topiello.example.expression.ExpressionLexicon.SUBTRACT;
import static org.topiello.example.expression.ExpressionLexicon.VARIABLE;

import java.util.Collection;
import java.util.stream.Stream;

import org.topiello.grammar.contextfree.Symbol;
import org.topiello.language.Sign;
import org.topiello.semantics.Denotation;
import org.topiello.semantics.Semantics;

/*
 * TODO parity between typing information in programming API and data format.
 * 
 * In API, generic type parameter on Pattern is useful for assigning terms to a
 * rule.
 * 
 * In format, types of rule productions are inferred based on their location.
 * 
 * Don't want API to have more features than format, generics 
 * 
 * -
 * 
 * How about here? Do we need to use variables? Should we be using something
 * else? There's nothing really connecting the input here to the actual types of
 * 
 * ;
 */
public class ExpressionSemantics implements Semantics<Symbol> {
  public static final Sign<Expression> EXPRESSION = new Sign<>();
  private Collection<Denotation<? extends Expression, Symbol>> denotations;

  public ExpressionSemantics(ExpressionGrammar grammar, ExpressionLexicon lexicon) {
    denotations = asList(

        new Denotation<>(
            EXPRESSION,
            MULTIPLY,
            Multiplication.class,
            (e, i) -> i.factors().forEach(a -> e.put(EXPRESSION, a)),
            d -> new Multiplication(d.getAll(EXPRESSION).collect(toList()))),

        new Denotation<>(
            EXPRESSION,
            DIVIDE,
            Division.class,
            (e, i) -> e.putAll(EXPRESSION, i.dividend(), i.divisor()),
            d -> new Division(d.get(EXPRESSION), d.get(EXPRESSION))),

        new Denotation<>(
            EXPRESSION,
            ADD,
            Addition.class,
            (e, i) -> i.addends().forEach(a -> e.put(EXPRESSION, a)),
            d -> new Addition(d.getAll(EXPRESSION).collect(toList()))),

        new Denotation<>(
            EXPRESSION,
            SUBTRACT,
            Subtraction.class,
            (e, i) -> e.putAll(EXPRESSION, i.minuend(), i.subtrahend()),
            d -> new Subtraction(d.get(EXPRESSION), d.get(EXPRESSION))),

        new Denotation<>(
            EXPRESSION,
            VARIABLE,
            Var.class,
            (e, i) -> e.putTerminal(i.name()),
            d -> new Var(d.getTerminal()))

    );
  }

  @Override
  public Stream<? extends Denotation<?, Symbol>> getDenotation(Symbol rule) {
    return null;
  }
}
