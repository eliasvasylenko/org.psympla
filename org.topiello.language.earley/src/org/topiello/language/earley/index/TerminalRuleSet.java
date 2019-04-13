package org.topiello.language.earley.index;

import org.topiello.grammar.Rule;
import org.topiello.text.TextUnit;

// TODO value type?
public class TerminalRuleSet<T extends Rule<?>, C extends TextUnit>
    extends IndexedBitSet<IndexedLanguage<T, C>, TerminalRule<T, C>> {
  TerminalRuleSet(IndexedLanguage<T, C> indexedRules) {
    super(indexedRules);
  }

  @Override
  protected int domainSize(IndexedLanguage<T, C> domain) {
    return domain.terminalRuleCount();
  }

  @Override
  protected TerminalRule<T, C> get(IndexedLanguage<T, C> domain, int index) {
    return domain.terminalRule(index);
  }

  @Override
  protected int indexOf(IndexedLanguage<T, C> domain, TerminalRule<T, C> element) {
    return element.index();
  }
}
