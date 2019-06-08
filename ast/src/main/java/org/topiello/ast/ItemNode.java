package org.topiello.ast;

import java.util.Optional;

import org.topiello.grammar.Product;
import org.topiello.grammar.Variable;

public interface ItemNode extends ExecutableNode {
  Variable variable();

  int dotPosition();

  Product nextProduct();

  ItemNode.Specialized previousItem();

  ExecutableNode predicted();

  interface Specialized extends ItemNode {
    Optional<ItemNode> nextItem();

    ExecutableNode advanced();
  }

  interface Specializable extends ItemNode {
    Specialized specialize(Variable derivation);
  }
}
