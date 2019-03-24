package org.psympla.language.earley;

import org.psympla.grammar.Grammar;
import org.psympla.language.Designation;
import org.psympla.lexicon.Characters;
import org.psympla.lexicon.Lexicon;
import org.psympla.semantics.Context;
import org.psympla.semantics.Sign;

public class EarleyDesignation<C, T> implements Designation<C, T> {
  private final Lexicon<C> lexicon;
  private final Grammar grammar;
  private final Sign<T> sign;

  public EarleyDesignation(Lexicon<C> lexicon, Grammar grammar, Sign<T> sign) {
    this.lexicon = lexicon;
    this.grammar = grammar;
    this.sign = sign;
  }

  @Override
  public Sign<T> sign() {
    return sign;
  }

  @Override
  public T decode(Characters<C> encoding, Context context) {
    
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Characters<C> encode(T information, Context context) {
    // TODO Auto-generated method stub
    return null;
  }

}
