package org.psympla.semantics;

public interface Context {
  <T> T getContext(Class<T> type);

  <T> T setContext(Class<T> type);
}
