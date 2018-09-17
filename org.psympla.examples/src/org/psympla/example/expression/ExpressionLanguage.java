package org.psympla.example.expression;

import org.psympla.parser.earley.EarleyLanguage;
import org.psympla.semantics.Sign;
import org.psympla.text.CodePoint;
import org.psympla.text.Text;

public class ExpressionLanguage extends EarleyLanguage<CodePoint> {
  public static final String NAMESPACE = ExpressionLanguage.class.getPackageName();
  public static final Sign<Expression> EXPRESSION = new Sign<>(NAMESPACE, "expression");
  public static final Sign<Addition> ADD = new Sign<>(NAMESPACE, "add");
  public static final Sign<Subtraction> SUBTRACT = new Sign<>(NAMESPACE, "subtract");
  public static final Sign<Multiplication> MULTIPLY = new Sign<>(NAMESPACE, "multiply");
  public static final Sign<Division> DIVIDE = new Sign<>(NAMESPACE, "divide");
  public static final Sign<Paranthesis> PARANTHESIS = new Sign<>(NAMESPACE, "parenthesis");
  public static final Sign<Variable> VARIABLE = new Sign<>(NAMESPACE, "variable");

  public static void main(String... args) {
    Expression expression = new ExpressionLanguage().decode(EXPRESSION, new Text<>("a + b * c"));
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
