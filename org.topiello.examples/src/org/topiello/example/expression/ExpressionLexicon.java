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
        new ContextFreeLexicalClass<>(CODE_POINTS, "[A-Za-z]", v),
        new ContextFreeLexicalClass<>(CODE_POINTS, "\\*", MULTIPLY),
        new ContextFreeLexicalClass<>(CODE_POINTS, "/", DIVIDE),
        new ContextFreeLexicalClass<>(CODE_POINTS, "+", ADD),
        new ContextFreeLexicalClass<>(CODE_POINTS, "-", SUBTRACT),
        new ContextFreeLexicalClass<>(CODE_POINTS, "(", OPEN_BRACKET),
        new ContextFreeLexicalClass<>(CODE_POINTS, ")", CLOSE_BRACKET));
  }

  @SuppressWarnings("unchecked")
  public ContextFreeLexicalClass<Symbol, UtfCodePoint> variable() {
    return (ContextFreeLexicalClass<UtfCodePoint>) getLexicalClass(VARIABLE);
  }

  @SuppressWarnings("unchecked")
  public ContextFreeLexicalClass<Symbol, UtfCodePoint> operator(OperatorSymbol symbol) {
    return (ContextFreeLexicalClass<, UtfCodePoint>) getLexicalClass(symbol);
  }
}
