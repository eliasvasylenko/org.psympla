package org.psympla.lexicon;

import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;

public class Token {
  private final Symbol name;
  private final LexicalItem<?> value;

  public Token(Symbol name, LexicalItem<?> value) {
    this.name = name;
    this.value = value;
  }

  public Symbol name() {
    return name;
  }

  public LexicalItem<?> value() {
    return value;
  }
}
