package org.topiello.ast.interpreted;

import org.topiello.ast.EvaluableNode;
import org.topiello.earley.EarleyState;

public interface InterpretedEvaluate<T> extends EvaluableNode<T> {
  T evaluate(EarleyState<?> state);
}
