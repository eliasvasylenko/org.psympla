package org.topiello.language.earley.index;

import org.topiello.grammar.Rule;

// TODO value type?
public class RuleSet<T extends Rule<?>>
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
