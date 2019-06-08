package org.topiello.ast.interpreted;

import org.topiello.ast.ExecutableNode;
import org.topiello.earley.EarleyState;

public interface InterpretedExecute extends ExecutableNode {
  void execute(EarleyState<?> state);
}
