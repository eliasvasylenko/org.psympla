package org.topiello.ast;

import java.util.Set;

import org.topiello.derivation.ParseNode;
import org.topiello.grammar.Product;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface GrammarNode<T extends Product, C extends TextUnit> {
  Set<? extends RuleNode> getRules();

  ParseNode parse(T product, Text<C> text);
}
