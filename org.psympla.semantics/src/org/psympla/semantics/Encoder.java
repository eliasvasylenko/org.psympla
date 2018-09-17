package org.psympla.semantics;

public interface Encoder<T> {
  void encode(EncodeState<T> encodeState, T information);
}
