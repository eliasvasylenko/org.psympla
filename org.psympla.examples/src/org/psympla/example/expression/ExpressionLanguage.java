package org.psympla.example.expression;

import static org.psympla.example.expression.ExpressionSemantics.EXPRESSION;

import org.psympla.language.earley.EarleyLanguage;
import org.psympla.text.CodePoint;
import org.psympla.text.Text;

public class ExpressionLanguage extends EarleyLanguage<CodePoint> {
  public static void main(String... args) {
    Expression expression = new ExpressionLanguage()
        .designation(EXPRESSION)
        .decode(new Text<>("a + b * c"));
    System.out.println(expression);
  }

  public ExpressionLanguage() {
    this(new ExpressionLexicon());
  }

  private ExpressionLanguage(ExpressionLexicon lexicon) {
    this(lexicon, new ExpressionGrammar(lexicon));
  }

  private ExpressionLanguage(ExpressionLexicon lexicon, ExpressionGrammar grammar) {
    super(lexicon, grammar, new ExpressionSemantics(grammar, lexicon));
  }
}
