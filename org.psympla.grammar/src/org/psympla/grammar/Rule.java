package org.psympla.grammar;

import static org.psympla.pattern.Patterns.literal;
import static org.psympla.pattern.Patterns.term;

import org.psympla.pattern.Pattern;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Nil;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Term;

public class Rule {
  private final Symbol symbol;
  private final Pattern<? extends LexicalItem> parameter;
  private final Production production;

  public Rule(Symbol symbol, Production production) {
    this(symbol, literal(Nil.instance()), production);
  }

  public Rule(Symbol symbol, Pattern<? extends LexicalItem> parameter, Production production) {
    this.symbol = symbol;
    this.parameter = parameter;
    this.production = production;
  }

  public Symbol symbol() {
    return symbol;
  }

  public Pattern<? extends LexicalItem> parameter() {
    return parameter;
  }

  public Pattern<Term> pattern() {
    return term(symbol, parameter);
  }

  public Production production() {
    return production;
  }
}
