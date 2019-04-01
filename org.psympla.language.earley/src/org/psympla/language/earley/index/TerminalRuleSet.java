package org.psympla.language.earley.index;

import org.psympla.text.TextUnit;

// TODO value type?
public class TerminalRuleSet<C extends TextUnit>
    extends IndexedBitSet<IndexedLanguage<C>, TerminalRule<C>> {
  TerminalRuleSet(IndexedLanguage<C> indexedRules) {
    super(indexedRules);
  }

  @Override
  protected int domainSize(IndexedLanguage<C> domain) {
    return domain.terminalRuleCount();
  }

  @Override
  protected TerminalRule<C> get(IndexedLanguage<C> domain, int index) {
    return domain.terminalRule(index);
  }

  @Override
  protected int indexOf(IndexedLanguage<C> domain, TerminalRule<C> element) {
    return element.index();
  }
}
