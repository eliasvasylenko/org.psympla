package org.psympla.example.expression;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.psympla.example.expression.ExpressionLexicon.ADD;
import static org.psympla.example.expression.ExpressionLexicon.DIVIDE;
import static org.psympla.example.expression.ExpressionLexicon.MULTIPLY;
import static org.psympla.example.expression.ExpressionLexicon.SUBTRACT;
import static org.psympla.example.expression.ExpressionLexicon.VARIABLE;

import java.util.Collection;

import org.psympla.pattern.Patterns;
import org.psympla.pattern.Variable;
import org.psympla.semantics.Denotation;
import org.psympla.semantics.Designation;
import org.psympla.semantics.Semantics;
import org.psympla.semantics.Sign;
import org.psympla.symbol.Value;

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

  public ExpressionSemantics(ExpressionGrammar grammar, ExpressionLexicon lexicon) {
    super(denotations(grammar, lexicon));
  }

  private static Collection<? extends Denotation<?>> denotations(
      ExpressionGrammar grammar,
      ExpressionLexicon lexicon) {
    var varName = new Variable<Value<String>>("T");

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
            (e, i) -> e.putInstantiation(varName, new Value<>(i.name())),
            d -> new Var(d.getInstantiation(varName).toString())),

        new Denotation<>(
            new Designation<>(EXPRESSION, new Variable("T"), Expression.class),
            (e, i) -> e.put(EXPRESSION, i),
            d -> d.get(EXPRESSION)));
  }

}
