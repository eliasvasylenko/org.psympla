package org.psympla.language.earley.index;

// TODO value type?
public class TerminalRuleSet<C> extends IndexedBitSet<TerminalRuleIndex<C>, TerminalRule<C>> {
  TerminalRuleSet(TerminalRuleIndex<C> indexedRules) {
    super(indexedRules);
  }

  @Override
  protected int domainSize(TerminalRuleIndex<C> domain) {
    return domain.count();
  }

  @Override
  protected TerminalRule<C> get(TerminalRuleIndex<C> domain, int index) {
    return domain.getRule(index);
  }

  @Override
  protected int indexOf(TerminalRuleIndex<C> domain, TerminalRule<C> element) {
    return element.index();
  }
}
