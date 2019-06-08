package org.topiello.ast.interpreted;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

import org.topiello.ast.GrammarNode;
import org.topiello.derivation.ParseNode;
import org.topiello.earley.EarleyState;
import org.topiello.grammar.Product;
import org.topiello.grammar.Rule;
import org.topiello.grammar.Variable;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class InterpretedGrammar<T extends Product, C extends TextUnit>
    implements GrammarNode<T, C> {
  private static final Variable ACCEPT_SYMBOL = new Variable() {};

  private final Set<InterpretedRule> rules = new HashSet<>();

  private final InterpretedTopiello topiello;

  public InterpretedGrammar(InterpretedTopiello topiello) {
    this.topiello = topiello;
  }

  InterpretedRule addRule(InterpretedRule rule) {
    rules.add(rule);
    return rule;
  }

  @Override
  public Set<InterpretedRule> getRules() {
    return unmodifiableSet(rules);
  }

  @Override
  public ParseNode parse(T product, Text<C> text) {
    var startRule = topiello.addAnonymousRule(this, new Rule<>(ACCEPT_SYMBOL, product));
    var state = new EarleyState<>();
    startRule.predicted().execute(state);
    throw new UnsupportedOperationException();
  }
}
