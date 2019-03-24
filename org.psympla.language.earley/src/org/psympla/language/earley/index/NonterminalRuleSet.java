package org.psympla.language.earley.index;

// TODO value type?
public class NonterminalRuleSet extends IndexedBitSet<IndexedLanguage<?>, NonterminalRule> {
  NonterminalRuleSet(IndexedLanguage<?> indexedRules) {
    super(indexedRules);
  }

  @Override
  protected int domainSize(IndexedLanguage<?> domain) {
    return domain.nonterminalRuleCount();
  }

  @Override
  protected NonterminalRule get(IndexedLanguage<?> domain, int index) {
    return domain.nonterminalRule(index);
  }

  @Override
  protected int indexOf(IndexedLanguage<?> domain, NonterminalRule element) {
    return element.index();
  }
}
