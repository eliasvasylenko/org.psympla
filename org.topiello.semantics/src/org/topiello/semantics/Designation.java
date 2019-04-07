package org.topiello.semantics;

import static org.topiello.pattern.Patterns.literal;

import org.topiello.pattern.Pattern;
import org.topiello.symbol.LexicalItem;

public class Designation<T> {
  private final Sign<? super T> sign;
  private final Pattern parameter;
  private final Class<T> type;

  public Designation(Sign<? super T> sign, LexicalItem parameter, Class<T> type) {
    this(sign, literal(parameter), type);
  }

  public Designation(Sign<? super T> sign, Pattern parameter, Class<T> type) {
    this.sign = sign;
    this.parameter = parameter;
    this.type = type;
  }

  public Sign<? super T> sign() {
    return sign;
  }

  public Pattern getParameter() {
    return parameter;
  }

  public Class<T> type() {
    return type;
  }
}
