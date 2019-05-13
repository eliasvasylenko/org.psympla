package org.topiello.grammar;

import org.topiello.text.TextUnit;

/**
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 *          the type attached to the node that appears in the parse tree for
 *          this rule.
 */
public interface Terminal<C extends TextUnit> {
  Variable variable();
}
