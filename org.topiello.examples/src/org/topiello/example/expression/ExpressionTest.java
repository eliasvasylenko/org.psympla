package org.topiello.example.expression;

import static org.topiello.example.expression.ExpressionGrammar.EXPRESSION;

import org.topiello.earley.EarleyParser;

public class ExpressionTest {
  public static void main(String... args) {
    var grammar = ExpressionGrammar.instance();
    new EarleyParser<>(grammar, EXPRESSION);
  }
}
