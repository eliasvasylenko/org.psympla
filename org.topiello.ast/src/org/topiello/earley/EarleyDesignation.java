package org.topiello.earley;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Product;
import org.topiello.language.Context;
import org.topiello.language.Designation;
import org.topiello.language.Sign;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class EarleyDesignation<T extends Product, C extends TextUnit, D>
    implements Designation<C, D> {
  private final Grammar<T, C> grammar;
  private final Sign<D> sign;

  public EarleyDesignation(Grammar<T, C> grammar, Sign<D> sign) {
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
