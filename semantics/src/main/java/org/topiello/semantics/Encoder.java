package org.topiello.semantics;

public interface Encoder<T> {
  void encode(EncodeState encodeState, T information);
}
