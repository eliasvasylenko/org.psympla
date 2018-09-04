package org.psympla.language;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;
import org.psympla.semantics.Semantics;

public class Language<C> {
  private final Lexicon<C> lexicon;
  private final Grammar grammar;
  private final Semantics semantics;

  public Language(Lexicon<C> lexicon, Grammar grammar, Semantics semantics) {
    this.lexicon = lexicon;
    this.grammar = grammar;
    this.semantics = semantics;
  }

  public Lexicon<C> lexicon() {
    return lexicon;
  }

  public Grammar grammar() {
    return grammar;
  }

  public Semantics semantics() {
    return semantics;
  }
  
  
}
