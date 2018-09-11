package org.psympla.parser.earley;

import org.psympla.grammar.Grammar;
import org.psympla.language.Language;
import org.psympla.lexicon.Lexicon;
import org.psympla.lexicon.Sequence;
import org.psympla.semantics.Context;
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

    ;
  }

  @Override
  public <T> T decode(Sign<T> sign, Sequence<C> encoding, Context context) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> Sequence<C> encode(Sign<T> sign, T information, Context context) {
    // TODO Auto-generated method stub
    return null;
  }
}
