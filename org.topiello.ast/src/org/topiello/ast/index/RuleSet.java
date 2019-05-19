package org.topiello.ast.index;

import org.topiello.grammar.Product;

// TODO value type?
public class RuleSet<T extends Product>
    extends IndexedBitSet<IndexedLanguage<T, ?>, IndexedRule<T>> {
  RuleSet(IndexedLanguage<T, ?> indexedRules) {
    super(indexedRules);
  }

  @Override
  protected int domainSize(IndexedLanguage<T, ?> domain) {
    return domain.ruleCount();
  }

  @Override
  protected IndexedRule<T> get(IndexedLanguage<T, ?> domain, int index) {
    return domain.rule(index);
  }

  @Override
  protected int indexOf(IndexedLanguage<T, ?> domain, IndexedRule<T> element) {
    return element.index();
  }
}
