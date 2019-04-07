package org.topiello.semantics;

public interface Decoder<T> {
  T decode(DecodeState decodeState);
}
