package org.psympla.example.expression;

import java.util.List;

import org.psympla.lexicon.Lexicon;
import org.psympla.symbol.Symbol;
import org.psympla.text.scanning.LiteralLexicalClass;
import org.psympla.text.scanning.RegexLexicalClass;
import org.psympla.text.utf.UtfCodePoint;

public class ExpressionLexicon extends Lexicon<UtfCodePoint> {
  public static class OperatorSymbol extends Symbol {
    public OperatorSymbol(String id) {
      super(id);
    }
  }

  public static final OperatorSymbol MULTIPLY = new OperatorSymbol("multiply");
  public static final OperatorSymbol DIVIDE = new OperatorSymbol("divide");
  public static final OperatorSymbol ADD = new OperatorSymbol("add");
  public static final OperatorSymbol SUBTRACT = new OperatorSymbol("subtract");
  public static final OperatorSymbol OPEN_BRACKET = new OperatorSymbol("open-bracket");
  public static final OperatorSymbol CLOSE_BRACKET = new OperatorSymbol("close-bracket");
  public static final Symbol VARIABLE = new Symbol("variable");

  public ExpressionLexicon() {
    super(
        List
            .of(
                new RegexLexicalClass<>(VARIABLE, "[A-Za-z]"),
                new LiteralLexicalClass<>(MULTIPLY, "\\*"),
                new LiteralLexicalClass<>(DIVIDE, "/"),
                new LiteralLexicalClass<>(ADD, "+"),
                new LiteralLexicalClass<>(SUBTRACT, "-"),
                new LiteralLexicalClass<>(OPEN_BRACKET, "("),
                new LiteralLexicalClass<>(CLOSE_BRACKET, ")")));
  }

  @SuppressWarnings("unchecked")
  public RegexLexicalClass<UtfCodePoint> variable() {
    return (RegexLexicalClass<UtfCodePoint>) getLexicalClass(VARIABLE);
  }

  @SuppressWarnings("unchecked")
  public LiteralLexicalClass<UtfCodePoint> operator(OperatorSymbol symbol) {
    return (LiteralLexicalClass<UtfCodePoint>) getLexicalClass(symbol);
  }
}
