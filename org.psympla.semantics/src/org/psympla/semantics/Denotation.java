package org.psympla.semantics;

/**
 * A denotation specifies the relationship between a signifier and the
 * information it represents.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 */
public class Denotation<T> implements Encoder<T>, Decoder<T> {
  private final Sign<T> sign;
  private final Encoder<T> encoder;
  private final Decoder<T> decoder;

  public Denotation(Sign<T> sign, Encoder<T> encoder, Decoder<T> decoder) {
    this.sign = sign;
    this.encoder = encoder;
    this.decoder = decoder;
  }

  public Sign<T> sign() {
    return sign;
  }

  @Override
  public void encode(EncodeState<T> encodeState, T information) {
    encoder.encode(encodeState, information);
  }

  @Override
  public T decode(DecodeState decodeState) {
    return decoder.decode(decodeState);
  }
}
