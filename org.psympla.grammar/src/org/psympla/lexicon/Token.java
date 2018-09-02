package org.psympla.lexicon;

import org.psympla.grammar.Term;

public class Token {
  private final Term term;

  public Token(Term term) {
    this.term = term;
  }

  public Term term() {
    return term;
  }
}
