package org.topiello.semantics;

/**
 * A denotation specifies the relationship between a signifier and the
 * information it represents.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 */
public class Denotation<T> {
  private final Designation<T> meaning;
  private final Encoder<T> encoder;
  private final Decoder<T> decoder;

  public Denotation(Designation<T> meaning, Encoder<T> encoder, Decoder<T> decoder) {
    this.meaning = meaning;
    this.encoder = encoder;
    this.decoder = decoder;
  }

  public Designation<T> meaning() {
    return meaning;
  }

  public Encoder<T> encoder() {
    return encoder;
  }

  public Decoder<T> decoder() {
    return decoder;
  }
}
