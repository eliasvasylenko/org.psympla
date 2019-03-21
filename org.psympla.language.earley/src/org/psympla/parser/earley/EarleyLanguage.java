package org.psympla.parser.earley;

import org.psympla.grammar.Grammar;
import org.psympla.language.Designation;
import org.psympla.language.Language;
import org.psympla.lexicon.Lexicon;
import org.psympla.parser.earley.products.ProductionClosures;
import org.psympla.semantics.Semantics;
import org.psympla.semantics.Sign;

public class EarleyLanguage<C> implements Language<C> {
  private final Lexicon<C> lexicon;
  private final Grammar grammar;
  private final Semantics semantics;

  public EarleyLanguage(Lexicon<C> lexicon, Grammar grammar, Semantics semantics) {
    this.lexicon = lexicon;
    this.grammar = grammar;
    this.semantics = semantics;

    var productions = new ProductionClosures(grammar, lexicon);
  }

  @Override
  public <T> Designation<C, T> designation(Sign<T> sign) {
    return new EarleyDesignation<>(lexicon, grammar, sign);
  }
}
