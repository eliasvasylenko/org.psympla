package org.psympla.semantics;

import org.psympla.symbol.SymbolicExpression;

/**
 * A handler specifies the relationship between symbolic expressions in a
 * grammar and the domain objects they represent by implementing a bijective
 * function between them.
 * <p>
 * Not all symbols need to be handled. For example delimiter terminals or
 * logical non-terminals such as aggregators with no useful state of their own.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 */
public interface Denotation<T> {
  Signifier<T> signifier();

  T composeMeaning(SignifiersIn production, SymbolicExpression parameters);

  SymbolicExpression decomposeMeaning(SignifiersOut production, T domainObject);
}
