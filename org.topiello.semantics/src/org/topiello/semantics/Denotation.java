package org.topiello.semantics;

import org.topiello.grammar.Rule;
import org.topiello.language.Sign;

/**
 * A denotation specifies the relationship between a signifier and the
 * information it represents.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 */
public class Denotation<T, U extends Rule<?>> {
  private final Sign<? super T> sign;
  private final Class<T> type;
  private final Encoder<T> encoder;
  private final Decoder<T> decoder;

  public Denotation(Sign<? super T> sign, Class<T> type, Encoder<T> encoder, Decoder<T> decoder) {
    this.sign = sign;
    this.type = type;
    this.encoder = encoder;
    this.decoder = decoder;
  }

  public Sign<? super T> sign() {
    return sign;
  }

  public Class<T> type() {
    return type;
  }

  public Encoder<T> encoder() {
    return encoder;
  }

  public Decoder<T> decoder() {
    return decoder;
  }
}
