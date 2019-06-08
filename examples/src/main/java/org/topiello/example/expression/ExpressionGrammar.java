package org.topiello.example.expression;

import static java.util.Arrays.asList;
import static org.topiello.example.expression.ExpressionLexicon.ADD;
import static org.topiello.example.expression.ExpressionLexicon.CLOSE_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.DIVIDE;
import static org.topiello.example.expression.ExpressionLexicon.MULTIPLY;
import static org.topiello.example.expression.ExpressionLexicon.OPEN_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.SUBTRACT;
import static org.topiello.example.expression.ExpressionLexicon.VARIABLE;

import org.topiello.grammar.Rule;
import org.topiello.grammar.contextfree.ContextFreeGrammar;
import org.topiello.grammar.contextfree.Symbol;
import org.topiello.text.utf.UtfCodePoint;

public class ExpressionGrammar extends ContextFreeGrammar<UtfCodePoint> {
  public static final Symbol BINARY_OPERATOR = new Symbol("binary-operator");
  public static final Symbol EXPRESSION = new Symbol("expression");

  private static final ExpressionGrammar INSTANCE = new ExpressionGrammar();

  public static ExpressionGrammar instance() {
    return INSTANCE;
  }

  private ExpressionGrammar() {
    super(
        new ExpressionLexicon(),
        asList(

            new Rule<>(EXPRESSION, VARIABLE),

            new Rule<>(EXPRESSION, EXPRESSION, MULTIPLY, EXPRESSION),
            new Rule<>(EXPRESSION, EXPRESSION, DIVIDE, EXPRESSION),
            new Rule<>(EXPRESSION, EXPRESSION, ADD, EXPRESSION),
            new Rule<>(EXPRESSION, EXPRESSION, SUBTRACT, EXPRESSION),

            new Rule<>(EXPRESSION, OPEN_BRACKET, EXPRESSION, CLOSE_BRACKET)

        ));
  }
}
