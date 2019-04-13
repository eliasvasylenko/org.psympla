package org.topiello.example.expression;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.topiello.example.expression.ExpressionLexicon.ADD;
import static org.topiello.example.expression.ExpressionLexicon.DIVIDE;
import static org.topiello.example.expression.ExpressionLexicon.MULTIPLY;
import static org.topiello.example.expression.ExpressionLexicon.SUBTRACT;
import static org.topiello.example.expression.ExpressionLexicon.VARIABLE;
import static org.topiello.pattern.Patterns.variable;

import java.util.Collection;

import org.topiello.semantics.Denotation;
import org.topiello.semantics.Designation;
import org.topiello.semantics.Semantics;
import org.topiello.semantics.Sign;
import org.topiello.semantics.Unknown;
import org.topiello.symbol.Value;

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
public class ExpressionSemantics extends Semantics {
  public static final Sign<Expression> EXPRESSION = new Sign<>(ExpressionGrammar.EXPRESSION);

  private static final Unknown<Value<String>> VARIABLE_NAME = new Unknown<>("Name", null);

  public ExpressionSemantics(ExpressionGrammar grammar, ExpressionLexicon lexicon) {
    super(denotations(grammar, lexicon));
  }

  private static Collection<? extends Denotation<?>> denotations(
      ExpressionGrammar grammar,
      ExpressionLexicon lexicon) {
    return asList(

        new Denotation<>(
            new Designation<>(
                EXPRESSION,
                lexicon.operator(MULTIPLY).instance(),
                Multiplication.class),
            (e, i) -> i.factors().forEach(a -> e.put(EXPRESSION, a)),
            d -> new Multiplication(d.getAll(EXPRESSION).collect(toList()))),

        new Denotation<>(
            new Designation<>(EXPRESSION, DIVIDE, Division.class),
            (e, i) -> e.putAll(EXPRESSION, i.dividend(), i.divisor()),
            d -> new Division(d.get(EXPRESSION), d.get(EXPRESSION))),

        new Denotation<>(
            new Designation<>(EXPRESSION, ADD, Addition.class),
            (e, i) -> i.addends().forEach(a -> e.put(EXPRESSION, a)),
            d -> new Addition(d.getAll(EXPRESSION).collect(toList()))),

        new Denotation<>(
            new Designation<>(EXPRESSION, SUBTRACT, Subtraction.class),
            (e, i) -> e.putAll(EXPRESSION, i.minuend(), i.subtrahend()),
            d -> new Subtraction(d.get(EXPRESSION), d.get(EXPRESSION))),

        new Denotation<>(
            new Designation<>(EXPRESSION, VARIABLE, Var.class),
            (e, i) -> e.putInstantiation(VARIABLE_NAME, new Value<>(i.name())),
            d -> new Var(d.getInstantiation(VARIABLE_NAME).get())),

        new Denotation<>(
            new Designation<>(EXPRESSION, variable("T"), Expression.class),
            (e, i) -> e.put(EXPRESSION, i),
            d -> d.get(EXPRESSION))

    );
  }
}