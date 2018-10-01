package org.psympla.example.expression;

import static java.util.Arrays.asList;
import static org.psympla.example.expression.ExpressionLexicon.NAMESPACE;

import java.util.List;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Production;
import org.psympla.grammar.Rule;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.TextItem;
import org.psympla.symbol.Variable;

public class ExpressionGrammar extends Grammar {
  public static final Symbol EXPRESSION = new Symbol(NAMESPACE, "expression");

  protected ExpressionGrammar(ExpressionLexicon lexicon) {
    super(rules(lexicon));
  }

  private static List<Rule> rules(ExpressionLexicon lexicon) {
    return asList(

        new Rule(
            EXPRESSION,
            new Production(lexicon.variable().instance(Variable.named("V").typed(TextItem.class)))),

        new Rule(
            EXPRESSION,
            new Production(EXPRESSION, lexicon.operator().instance("*"), EXPRESSION)),

        new Rule(
            EXPRESSION,
            new Production(EXPRESSION, lexicon.operator().instance("/"), EXPRESSION)),

        new Rule(
            EXPRESSION,
            new Production(EXPRESSION, lexicon.operator().instance("+"), EXPRESSION)),

        new Rule(
            EXPRESSION,
            new Production(EXPRESSION, lexicon.operator().instance("-"), EXPRESSION)),

        new Rule(
            EXPRESSION,
            new Production(
                lexicon.operator().instance("("),
                EXPRESSION,
                lexicon.operator().instance(")")))

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