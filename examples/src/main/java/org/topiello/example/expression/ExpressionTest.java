package org.topiello.example.expression;

import static org.topiello.example.expression.ExpressionGrammar.EXPRESSION;

import org.topiello.ast.GrammarNode;
import org.topiello.ast.interpreted.InterpretedTopiello;
import org.topiello.grammar.contextfree.Symbol;
import org.topiello.text.utf.UtfCodePoint;

public class ExpressionTest {
  public static void main(String... args) {
    var grammar = ExpressionGrammar.instance();
    GrammarNode<Symbol, UtfCodePoint> grammarNode = new InterpretedTopiello().createGrammar();

    var text = UtfCodePoint.CODE_POINTS.fromChars(args[0]);

    grammarNode.parse(EXPRESSION, text);
  }
}
