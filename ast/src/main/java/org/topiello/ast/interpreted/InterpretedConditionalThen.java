package org.topiello.ast.interpreted;

import org.topiello.earley.EarleyState;

public class InterpretedConditionalThen implements InterpretedExecute {
  private final InterpretedEvaluate<Boolean> condition;
  private final InterpretedExecute ifThen;

  public InterpretedConditionalThen(
      InterpretedEvaluate<Boolean> condition,
      InterpretedExecute ifThen) {
    this.condition = condition;
    this.ifThen = ifThen;
  }

  @Override
  public void execute(EarleyState<?> state) {
    if (condition.evaluate(state)) {
      ifThen.execute(state);
    }
  }
}
