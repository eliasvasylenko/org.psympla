package org.topiello.grammar;

/**
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 *          the type attached to the node that appears in the parse tree for
 *          this rule.
 */
public interface Rule<T> {
  T variable();

  int length();

  Product<? extends T> product(int index);
}
