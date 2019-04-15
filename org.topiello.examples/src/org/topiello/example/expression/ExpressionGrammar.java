package org.topiello.example.expression;

import static java.util.Arrays.asList;
import static org.topiello.example.expression.ExpressionLexicon.ADD;
import static org.topiello.example.expression.ExpressionLexicon.CLOSE_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.DIVIDE;
import static org.topiello.example.expression.ExpressionLexicon.MULTIPLY;
import static org.topiello.example.expression.ExpressionLexicon.OPEN_BRACKET;
import static org.topiello.example.expression.ExpressionLexicon.SUBTRACT;
import static org.topiello.example.expression.ExpressionLexicon.VARIABLE;

import java.util.List;

import org.topiello.grammar.contextfree.ContextFreeGrammar;
import org.topiello.grammar.contextfree.ContextFreeRule;
import org.topiello.grammar.contextfree.Symbol;

public class ExpressionGrammar extends ContextFreeGrammar {
  public static final Symbol BINARY_OPERATOR = new Symbol("binary-operator");
  public static final Symbol EXPRESSION = new Symbol("expression");

  protected ExpressionGrammar(ExpressionLexicon lexicon) {
    super(rules(lexicon));
  }

  private static List<ContextFreeRule> rules(ExpressionLexicon lexicon) {
    return asList(

        new ContextFreeRule(EXPRESSION).withProduct(VARIABLE),

        new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, MULTIPLY, EXPRESSION),
        new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, DIVIDE, EXPRESSION),
        new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, ADD, EXPRESSION),
        new ContextFreeRule(EXPRESSION).withProducts(EXPRESSION, SUBTRACT, EXPRESSION),

        new ContextFreeRule(EXPRESSION).withProducts(OPEN_BRACKET, EXPRESSION, CLOSE_BRACKET)

    );
  }

  /*-
   * 
   * 
   * 
   * 
   * 
   * TODO consider precedence! Think of how it relates to the problem of ambiguity
   * in printing
   * 
   * Say we have the tree:
   * 
   *   mul
   *   |\
   *   a add
   *     |\
   *     b c
   * 
   * Naive printing would give: a * b + c, but then parsing that would give the
   * wrong tree:
   * 
   *   add
   *   |\
   *   mul c
   *   |\
   *   a b
   * 
   * So we want to print it as a * (b + c)
   * 
   * To detect and resolve this ambiguity we need some of the same tools as to
   * parse correct precedence in the first place.
   * 
   * 
   * 
   * !!! Answer:
   * 
   * Don't think of solving ambiguity as a part of the ongoing parse/deparse operation!
   * Instead think of ambiguity resolution as being a choice between two full trees. Which
   * is the better parse? Have an ordered set of precedence rules, the first one to match
   * a rule which is not also matched by the other wins. Rules can perhaps match from the
   * bottom up or from the top down, e.g. multiplication matches before addition from the
   * bottom up, also from left to right.
   * 
   * Then we can figure out how to apply these semantics to packed parse forests or even
   * apply them during parsing to prune early, if possible.
   * 
   * Can we then calculate whether all possible ambiguities are addressed? That would be great.
   * 
   * 
   */
}
