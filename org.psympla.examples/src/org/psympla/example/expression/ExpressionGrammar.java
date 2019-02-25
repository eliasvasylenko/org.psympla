package org.psympla.example.expression;

import static java.util.Arrays.asList;
import static org.psympla.example.expression.ExpressionLexicon.ADD;
import static org.psympla.example.expression.ExpressionLexicon.CLOSE_BRACKET;
import static org.psympla.example.expression.ExpressionLexicon.DIVIDE;
import static org.psympla.example.expression.ExpressionLexicon.MULTIPLY;
import static org.psympla.example.expression.ExpressionLexicon.NAMESPACE;
import static org.psympla.example.expression.ExpressionLexicon.OPEN_BRACKET;
import static org.psympla.example.expression.ExpressionLexicon.SUBTRACT;
import static org.psympla.example.expression.ExpressionLexicon.VARIABLE;
import static org.psympla.pattern.Patterns.cons;
import static org.psympla.pattern.Patterns.wildcard;

import java.util.List;

import org.psympla.constraint.Match;
import org.psympla.constraint.Or;
import org.psympla.grammar.Grammar;
import org.psympla.grammar.Rule;
import org.psympla.pattern.Variable;
import org.psympla.symbol.Symbol;

public class ExpressionGrammar extends Grammar {
  public static final Symbol EXPRESSION = new Symbol(NAMESPACE, "expression");

  protected ExpressionGrammar(ExpressionLexicon lexicon) {
    super(rules(lexicon));
  }

  private static List<Rule> rules(ExpressionLexicon lexicon) {
    var T = new Variable("T");

    return asList(
        
        new Rule(EXPRESSION, VARIABLE).withProduct(VARIABLE, wildcard()),

        new Rule(EXPRESSION, T)
            .withProduct(EXPRESSION)
            .withProduct(T)
            .withProduct(EXPRESSION)
            .withConstraint(
                new Or(
                    new Match(T, cons(MULTIPLY, wildcard())),
                    new Match(T, cons(DIVIDE, wildcard())),
                    new Match(T, cons(ADD, wildcard())),
                    new Match(T, cons(SUBTRACT, wildcard())))),

        new Rule(EXPRESSION, new Variable("T"))
            .withProduct(OPEN_BRACKET)
            .withProduct(EXPRESSION, new Variable("T"))
            .withProduct(CLOSE_BRACKET)

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