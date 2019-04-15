package org.topiello.pattern.impl;

import java.util.Optional;

import org.topiello.pattern.Cons;
import org.topiello.pattern.Literal;
import org.topiello.pattern.Pattern;
import org.topiello.symbol.Cell;
import org.topiello.symbol.LexicalItem;
import org.topiello.symbol.Symbol;

// TODO value class
class RulePattern {
  private final boolean parametric;
  private final Optional<Symbol> symbol;

  public RulePattern(Pattern pattern) {
    LexicalItem identifier;

    if (pattern.getClass() == Literal.class) {
      var lexicalItem = ((Literal) pattern).lexicalItem();

      this.parametric = lexicalItem.getClass() == Cell.class;
      identifier = parametric ? ((Cell<?, ?>) lexicalItem).car() : lexicalItem;

    } else {
      Pattern identifierPattern;

      this.parametric = pattern.getClass() == Cons.class;
      identifierPattern = parametric ? ((Cons) pattern).car() : pattern;
      identifier = identifierPattern instanceof Literal
          ? ((Literal) identifierPattern).lexicalItem()
          : null;
    }

    this.symbol = identifier instanceof Symbol
        ? Optional.of((Symbol) identifier)
        : Optional.empty();
  }

  public boolean parametric() {
    return parametric;
  }

  public Optional<Symbol> symbol() {
    return symbol;
  }
}