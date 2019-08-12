package org.topiello.ast;

import org.topiello.ast.interpreted.InterpretedTopiello;

public class AstTest {
  @Test
  public void randomStuffTest() {
    AstFactory topiello = new InterpretedTopiello();

    var grammar = topiello.createGrammar();
    topiello.addRule(grammar, rule);
  }
}
