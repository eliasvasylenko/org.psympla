package org.psympla.semantics;

public interface Decoder<T> {
  T decode(DecodeState decodeState);
}
