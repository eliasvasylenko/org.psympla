package org.topiello.ast.interpreted;

import org.topiello.ast.ExecutableNode;
import org.topiello.ast.RuleNode;
import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.grammar.Variable;

public class InterpretedSpecializableRule implements RuleNode.Specializable {
  private final Rule<?> rule;

  public InterpretedSpecializableRule(Rule<?> rule) {
    this.rule = rule;
  }

  @Override
  public Variable variable() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ExecutableNode predicted() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Specialized specialize(Product derivedFrom) {
    // TODO Auto-generated method stub
    return null;
  }
}
