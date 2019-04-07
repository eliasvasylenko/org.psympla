package org.topiello.language.earley;

import org.topiello.grammar.Grammar;
import org.topiello.language.Designation;
import org.topiello.language.Language;
import org.topiello.language.earley.index.IndexedLanguage;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Semantics;
import org.topiello.semantics.Sign;
import org.topiello.text.TextUnit;

public class EarleyLanguage<C extends TextUnit> implements Language<C> {
  private final Lexicon<C> lexicon;
  private final Grammar grammar;
  private final Semantics semantics;

  public EarleyLanguage(Lexicon<C> lexicon, Grammar grammar, Semantics semantics) {
    this.lexicon = lexicon;
    this.grammar = grammar;
    this.semantics = semantics;

    var productions = new IndexedLanguage<>(grammar, lexicon);
  }

  @Override
  public <T> Designation<C, T> designation(Sign<T> sign) {
    return new EarleyDesignation<>(lexicon, grammar, sign);
  }
}
