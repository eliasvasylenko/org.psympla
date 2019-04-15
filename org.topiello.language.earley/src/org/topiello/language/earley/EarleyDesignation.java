package org.topiello.language.earley;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Rule;
import org.topiello.language.Designation;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Context;
import org.topiello.semantics.Sign;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class EarleyDesignation<T extends Rule<?>, C extends TextUnit, D>
    implements Designation<C, D> {
  private final Lexicon<T, C> lexicon;
  private final Grammar<T> grammar;
  private final Sign<D> sign;

  public EarleyDesignation(Lexicon<T, C> lexicon, Grammar<T> grammar, Sign<D> sign) {
    this.lexicon = lexicon;
    this.grammar = grammar;
    this.sign = sign;
  }

  @Override
  public Sign<D> sign() {
    return sign;
  }

  @Override
  public D decode(Text<C> encoding, Context context) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Text<C> encode(D information, Context context) {
    // TODO Auto-generated method stub
    return null;
  }
}
