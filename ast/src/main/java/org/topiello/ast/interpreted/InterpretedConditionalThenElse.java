package org.topiello.ast.interpreted;

import org.topiello.earley.EarleyState;

public class InterpretedConditionalThenElse implements InterpretedExecute {
  private final InterpretedEvaluate<Boolean> condition;
  private final InterpretedExecute ifThen;
  private final InterpretedExecute elseThen;

  public InterpretedConditionalThenElse(
      InterpretedEvaluate<Boolean> condition,
      InterpretedExecute ifThen,
      InterpretedExecute elseThen) {
    this.condition = condition;
    this.ifThen = ifThen;
    this.elseThen = elseThen;
  }

  @Override
  public void execute(EarleyState<?> state) {
    if (condition.evaluate(state)) {
      ifThen.execute(state);
    } else {
      elseThen.execute(state);
    }
  }
}
