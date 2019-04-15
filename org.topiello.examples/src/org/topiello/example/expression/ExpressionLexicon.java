package org.topiello.example.expression;

import static org.topiello.text.utf.UtfCodePoint.CODE_POINTS;

import java.util.List;
import java.util.stream.Stream;

import org.topiello.grammar.contextfree.Symbol;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Lexicon;
import org.topiello.text.scanning.LiteralLexicalClass;
import org.topiello.text.scanning.RegexLexicalClass;
import org.topiello.text.utf.UtfCodePoint;

public class ExpressionLexicon implements Lexicon<Symbol, UtfCodePoint> {
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

  private final List<LexicalClass<Symbol, UtfCodePoint>> lexicalItems;

  public ExpressionLexicon() {
    lexicalItems = List
        .of(
            new RegexLexicalClass<>(VARIABLE, "[A-Za-z]"),
            new LiteralLexicalClass<>(CODE_POINTS, MULTIPLY, "\\*"),
            new LiteralLexicalClass<>(CODE_POINTS, DIVIDE, "/"),
            new LiteralLexicalClass<>(CODE_POINTS, ADD, "+"),
            new LiteralLexicalClass<>(CODE_POINTS, SUBTRACT, "-"),
            new LiteralLexicalClass<>(CODE_POINTS, OPEN_BRACKET, "("),
            new LiteralLexicalClass<>(CODE_POINTS, CLOSE_BRACKET, ")"));
  }

  @SuppressWarnings("unchecked")
  public RegexLexicalClass<UtfCodePoint> variable() {
    return (RegexLexicalClass<UtfCodePoint>) getLexicalClass(VARIABLE);
  }

  @SuppressWarnings("unchecked")
  public LiteralLexicalClass<UtfCodePoint> operator(OperatorSymbol symbol) {
    return (LiteralLexicalClass<UtfCodePoint>) getLexicalClass(symbol);
  }

  @Override
  public Stream<LexicalClass<? extends Symbol, UtfCodePoint>> getLexicalClasses() {
    // TODO Auto-generated method stub
    return null;
  }
}
