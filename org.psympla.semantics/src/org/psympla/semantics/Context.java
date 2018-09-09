package org.psympla.semantics;

public interface Context {
  <T> T getContext(Class<T> type);

  <T> Context withContext(Class<T> type, T information);

  static Context empty() {
    return new Context() {
      @Override
      public <T> Context withContext(Class<T> type, T information) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public <T> T getContext(Class<T> type) {
        return null;
      }
    };
  }
}
