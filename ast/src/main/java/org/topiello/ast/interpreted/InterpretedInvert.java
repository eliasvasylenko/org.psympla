package org.topiello.ast.interpreted;

import org.topiello.earley.EarleyState;

public class InterpretedInvert implements InterpretedEvaluate<Boolean> {
  private final InterpretedEvaluate<Boolean> child;

  public InterpretedInvert(InterpretedEvaluate<Boolean> child) {
    this.child = child;
  }

  @Override
  public Boolean evaluate(EarleyState<?> state) {
    return !child.evaluate(state);
  }
}
