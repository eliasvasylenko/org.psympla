package org.topiello.ast.interpreted;

import org.topiello.ast.ItemNode;
import org.topiello.ast.RuleNode;
import org.topiello.grammar.Rule;
import org.topiello.grammar.Variable;

public class InterpretedRule implements RuleNode.Specialized {
  private final Rule<?> rule;

  public InterpretedRule(Rule<?> rule) {
    this.rule = rule;
  }

  @Override
  public Variable variable() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public InterpretedExecute predicted() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ItemNode firstItem() {
    // TODO Auto-generated method stub
    return null;
  }
}
