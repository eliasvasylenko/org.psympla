package org.psympla.example.expression;

import static java.util.Arrays.asList;

import java.util.Collection;

import org.psympla.pattern.Variable;
import org.psympla.semantics.Denotation;
import org.psympla.semantics.Meaning;
import org.psympla.semantics.Semantics;
import org.psympla.semantics.Sign;
import org.psympla.symbol.TextItem;

public class ExpressionSemantics extends Semantics {
  public static final Sign<Expression> EXPRESSION = new Sign<>(ExpressionGrammar.EXPRESSION);

  public ExpressionSemantics(ExpressionGrammar grammar, ExpressionLexicon lexicon) {
    super(denotations(grammar, lexicon));
  }

  private static Collection<? extends Denotation<?>> denotations(
      ExpressionGrammar grammar,
      ExpressionLexicon lexicon) {
    var varName = Variable.named("T").typed(TextItem.class);

    return asList(

        new Denotation<>(
            new Meaning<>(EXPRESSION, Var.class, lexicon.variable().instance(varName)),
            (e, i) -> e.putInstantiation(varName, new TextItem(i.name())),
            d -> new Var(d.getInstantiate(varName).toString())),

        new Denotation<>(
            new Meaning<>(EXPRESSION, Multiplication.class, lexicon.operator().instance("*")),
            (e, i) -> i.factors().forEach(a -> e.put(EXPRESSION, a)),
            d -> new Multiplication(d.getAll(EXPRESSION).collect(toList()))),

        new Denotation<>(
            new Meaning<>(EXPRESSION, Division.class, lexicon.operator().instance("/")),
            (e, i) -> e.putAll(EXPRESSION, i.dividend(), i.divisor()),
            d -> new Division(d.get(EXPRESSION), d.get(EXPRESSION))),

        new Denotation<>(
            new Meaning<>(EXPRESSION, Addition.class, lexicon.operator().instance("+")),
            (e, i) -> i.addends().forEach(a -> e.put(EXPRESSION, a)),
            d -> new Addition(d.getAll(EXPRESSION).collect(toList()))),

        new Denotation<>(
            new Meaning<>(EXPRESSION, Subtraction.class, lexicon.operator().instance("-")),
            (e, i) -> e.putAll(EXPRESSION, i.minuend(), i.subtrahend()),
            d -> new Subtraction(d.get(EXPRESSION), d.get(EXPRESSION)))

    );
  }
}
