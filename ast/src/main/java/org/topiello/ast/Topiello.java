package org.topiello.ast;

import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.text.TextUnit;

public interface Topiello {
  <T extends Product, U extends TextUnit> GrammarNode<T, U> createGrammar();

  RuleNode addRule(GrammarNode<?, ?> grammar, Rule<?> rule);

  RuleNode addAnonymousRule(GrammarNode<?, ?> grammar, Rule<?> rule);

  EvaluableNode<Boolean> invert(EvaluableNode<Boolean> node);

  ExecutableNode createConditional(EvaluableNode<Boolean> condition, ExecutableNode ifThen);

  ExecutableNode createConditional(
      EvaluableNode<Boolean> condition,
      ExecutableNode ifThen,
      ExecutableNode elseThen);
}
