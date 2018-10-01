package org.psympla.symbol;

import java.util.Optional;
import java.util.stream.Stream;

public interface Pattern<T extends LexicalItem<T>> {
  /**
   * @return the uninstantiated variables mentioned by this pattern
   */
  Stream<Variable<?>> getVariables();

  /**
   * The construct operation makes a substitution of all the variables mentioned
   * by the pattern for a given set of instantiations.
   * <p>
   * The inverse of this function is {@link #destructure(ParameterizedSymbol)}.
   * 
   * @param instantiations
   * @return
   */
  T construct(Instantiations instantiations);

  /**
   * The destructure operation is a pattern matcher. If possible, it finds the set
   * of instantiations for the variables mentioned by the pattern such that the
   * result is the given symbol. If this is not possible then it is said that the
   * given symbol does not match the pattern.
   * <p>
   * The inverse of this function is {@link #construct(Instantiations)}.
   * 
   * @param symbol
   * @return
   */
  Optional<Instantiations> destructure(T item);

  Pattern<T> partiallyConstruct(PartialInstantiations instantiations);

  Optional<Instantiations> partiallyDestructure(Pattern<T> pattern);
}
