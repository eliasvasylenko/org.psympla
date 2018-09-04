package org.psympla.symbol;

import java.util.Optional;
import java.util.stream.Stream;

public interface LexicalItem<T extends LexicalItem<T>> extends Pattern<T> {
  @SuppressWarnings("unchecked")
  @Override
  default T construct(Instantiations instantiations) {
    return (T) this;
  }

  @Override
  default Optional<Instantiations> destructure(T symbol) {
    if (equals(symbol)) {
      return Optional.of(Instantiations.empty());
    }
    return Optional.empty();
  }

  @Override
  default Stream<Variable<?>> getVariables() {
    return Stream.empty();
  }
}
