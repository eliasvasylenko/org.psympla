package org.topiello.language.earley.index;

import org.topiello.grammar.Rule;

// TODO value type?
public class NonterminalRuleSet<T extends Rule<?>>
    extends IndexedBitSet<IndexedLanguage<T, ?>, NonterminalRule<T>> {
  NonterminalRuleSet(IndexedLanguage<T, ?> indexedRules) {
    super(indexedRules);
  }

  @Override
  protected int domainSize(IndexedLanguage<T, ?> domain) {
    return domain.nonterminalRuleCount();
  }

  @Override
  protected NonterminalRule<T> get(IndexedLanguage<T, ?> domain, int index) {
    return domain.nonterminalRule(index);
  }

  @Override
  protected int indexOf(IndexedLanguage<T, ?> domain, NonterminalRule<T> element) {
    return element.index();
  }
}
