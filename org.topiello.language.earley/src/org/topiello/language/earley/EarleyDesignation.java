package org.topiello.language.earley;

import org.topiello.grammar.Grammar;
import org.topiello.language.Designation;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Context;
import org.topiello.semantics.Sign;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class EarleyDesignation<C extends TextUnit, T> implements Designation<C, T> {
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
  public T decode(Text<C> encoding, Context context) {
    
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Text<C> encode(T information, Context context) {
    // TODO Auto-generated method stub
    return null;
  }

}
