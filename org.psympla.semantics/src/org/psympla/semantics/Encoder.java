package org.psympla.semantics;

public interface Encoder<T> {
  void encode(EncodeState encodeState, T information);
}
