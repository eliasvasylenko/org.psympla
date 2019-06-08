package org.topiello.example.expression;

import static org.topiello.text.utf.UtfCodePoint.CODE_POINTS;

import org.topiello.grammar.contextfree.ContextFreeLexicalClass;
import org.topiello.grammar.contextfree.ContextFreeLexicon;
import org.topiello.grammar.contextfree.Symbol;
import org.topiello.text.utf.UtfCodePoint;

public class ExpressionLexicon extends ContextFreeLexicon<UtfCodePoint> {
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
        new ContextFreeLexicalClass.Regex<>(CODE_POINTS, VARIABLE, "[A-Za-z]"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, MULTIPLY, "\\*"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, DIVIDE, "/"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, ADD, "+"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, SUBTRACT, "-"),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, OPEN_BRACKET, "("),
        new ContextFreeLexicalClass.Literal<>(CODE_POINTS, CLOSE_BRACKET, ")"));
  }

  @SuppressWarnings("unchecked")
  public ContextFreeLexicalClass.Regex<UtfCodePoint> variable() {
    return (ContextFreeLexicalClass.Regex<UtfCodePoint>) getLexicalClass(VARIABLE).get();
  }

  @SuppressWarnings("unchecked")
  public ContextFreeLexicalClass.Literal<UtfCodePoint> operator(OperatorSymbol symbol) {
    return (ContextFreeLexicalClass.Literal<UtfCodePoint>) getLexicalClass(symbol).get();
  }
}
