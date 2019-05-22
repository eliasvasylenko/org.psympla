package org.topiello.example.expression;

import static java.util.Arrays.asList;
import static org.topiello.example.expression.ExpressionLexicon.ADD;
import static org.topiello.example.expression.ExpressionLexicon.CLOSE_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.DIVIDE;
import static org.topiello.example.expression.ExpressionLexicon.MULTIPLY;
import static org.topiello.example.expression.ExpressionLexicon.OPEN_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.SUBTRACT;
import static org.topiello.example.expression.ExpressionLexicon.VARIABLE;

import org.topiello.grammar.contextfree.ContextFreeGrammar;
import org.topiello.grammar.contextfree.ContextFreeRule;
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

            new ContextFreeRule(EXPRESSION).withProduct(VARIABLE),

            new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, MULTIPLY, EXPRESSION),
            new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, DIVIDE, EXPRESSION),
            new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, ADD, EXPRESSION),
            new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, SUBTRACT, EXPRESSION),

            new ContextFreeRule(EXPRESSION).withProducts(OPEN_BRACKET, EXPRESSION, CLOSE_BRACKET)

        ));
  }
}
