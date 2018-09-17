package org.psympla.example.expression;

import static java.util.Arrays.asList;

import org.psympla.lexicon.Lexicon;
import org.psympla.symbol.Symbol;
import org.psympla.text.CodePoint;
import org.psympla.text.scanning.Regex;

public class ExpressionLexicon extends Lexicon<CodePoint> {
  public static final String NAMESPACE = ExpressionLexicon.class.getPackageName();
  public static final Symbol OPERATOR = new Symbol(NAMESPACE, "operator");
  public static final Symbol VARIABLE = new Symbol(NAMESPACE, "variable");

  private final Regex<CodePoint> variable;
  private final Regex<CodePoint> operator;

  public ExpressionLexicon() {
    this(new Regex<>(VARIABLE, "[A-Za-z]"), new Regex<>(OPERATOR, "[+-*/()]"));
  }

  public ExpressionLexicon(Regex<CodePoint> variable, Regex<CodePoint> operator) {
    super(asList(variable, operator));
    this.variable = variable;
    this.operator = operator;
  }

  public Regex<CodePoint> variable() {
    return variable;
  }

  public Regex<CodePoint> operator() {
    return operator;
  }
}
