package org.topiello.ast.interpreted;

import org.topiello.ast.EvaluableNode;
import org.topiello.ast.ExecutableNode;
import org.topiello.ast.GrammarNode;
import org.topiello.ast.Topiello;
import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.text.TextUnit;

public class InterpretedTopiello implements Topiello {
  @Override
  public <T extends Product, U extends TextUnit> InterpretedGrammar<T, U> createGrammar() {
    return new InterpretedGrammar<>(this);
  }

  @Override
  public InterpretedRule addRule(GrammarNode<?, ?> grammar, Rule<?> rule) {
    return ((InterpretedGrammar<?, ?>) grammar).addRule(new InterpretedRule(rule));
  }

  @Override
  public InterpretedEvaluate<Boolean> invert(EvaluableNode<Boolean> node) {
    return new InterpretedInvert((InterpretedEvaluate<Boolean>) node);
  }

  @Override
  public InterpretedExecute createConditional(
      EvaluableNode<Boolean> condition,
      ExecutableNode ifThen) {
    return new InterpretedConditionalThen(
        (InterpretedEvaluate<Boolean>) condition,
        (InterpretedExecute) ifThen);
  }

  @Override
  public InterpretedExecute createConditional(
      EvaluableNode<Boolean> condition,
      ExecutableNode ifThen,
      ExecutableNode elseThen) {
    return new InterpretedConditionalThenElse(
        (InterpretedEvaluate<Boolean>) condition,
        (InterpretedExecute) ifThen,
        (InterpretedExecute) elseThen);
  }

  @Override
  public InterpretedRule addAnonymousRule(GrammarNode<?, ?> grammar, Rule<?> rule) {
    // TODO Auto-generated method stub
    return null;
  }
}
