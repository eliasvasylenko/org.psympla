package org.topiello.language.earley;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Rule;
import org.topiello.language.Designation;
import org.topiello.language.Language;
import org.topiello.language.earley.index.IndexedLanguage;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Semantics;
import org.topiello.semantics.Sign;
import org.topiello.text.TextUnit;

public class EarleyLanguage<T extends Rule<?>, C extends TextUnit> implements Language<T, C> {
  private final Lexicon<T, C> lexicon;
  private final Grammar<T> grammar;
  private final Semantics semantics;

  public EarleyLanguage(Lexicon<T, C> lexicon, Grammar<T> grammar, Semantics semantics) {
    this.lexicon = lexicon;
    this.grammar = grammar;
    this.semantics = semantics;

    var productions = new IndexedLanguage<>(grammar, lexicon);
  }

  @Override
  public <U> Designation<C, U> designation(Sign<U> sign) {
    return new EarleyDesignation<>(lexicon, grammar, sign);
  }
}
