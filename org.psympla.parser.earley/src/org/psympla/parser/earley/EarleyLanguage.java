package org.psympla.parser.earley;

import java.util.List;

import org.psympla.grammar.Grammar;
import org.psympla.language.Language;
import org.psympla.lexicon.Lexicon;
import org.psympla.semantics.Context;
import org.psympla.semantics.Semantics;
import org.psympla.semantics.Sign;

public class EarleyLanguage<C> implements Language<C> {
  public EarleyLanguage(Lexicon<C> lexicon, Grammar grammar, Semantics semantics) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public Language<C> language() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T decode(Sign<T> sign, List<C> encoding, Context context) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> List<C> encode(Sign<T> sign, T information, Context context) {
    // TODO Auto-generated method stub
    return null;
  }
}
