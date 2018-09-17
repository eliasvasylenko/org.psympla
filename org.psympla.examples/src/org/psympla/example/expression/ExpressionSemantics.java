package org.psympla.example.expression;

import static java.util.Arrays.asList;

import org.psympla.semantics.Denotation;
import org.psympla.semantics.Semantics;
import org.psympla.semantics.Sign;
import org.psympla.symbol.Cell;

public class ExpressionSemantics extends Semantics {
  public static final Sign<Expression> EXPRESSION_SIGN = new Sign<>(ExpressionGrammar.EXPRESSION);
  public static final Sign<Addition> ADD_SIGN = new Sign<>(ExpressionGrammar.ADD);
  public static final Sign<Subtraction> SUBTRACT_SIGN = new Sign<>(ExpressionGrammar.SUBTRACT);

  public ExpressionSemantics(ExpressionGrammar grammar, ExpressionLexicon lexicon) {
    super(
        asList(

            new Denotation<>(
                EXPRESSION_SIGN,
                (encoder, information) -> Cell.empty(),
                decoder -> decoder.get(ADD_SIGN)),

            new Denotation<>(
                EXPRESSION_SIGN,
                // TODO filter the following so we only match information of the class
                (encoder, information) -> encoder.put(SUBTRACT_SIGN, (Subtraction) information),
                // TODO filter the following so we only match encodings containing the sign
                decoder -> decoder.get(SUBTRACT_SIGN))

        ));
  }
}
