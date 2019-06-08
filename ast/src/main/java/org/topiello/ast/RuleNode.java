package org.topiello.ast;

import org.topiello.grammar.Product;
import org.topiello.grammar.Variable;

public interface RuleNode {
  Variable variable();

  ExecutableNode predicted();

  interface Specialized extends RuleNode {
    ItemNode firstItem();
  }

  interface Specializable extends RuleNode {
    Specialized specialize(Product derivedFrom);
  }
}
