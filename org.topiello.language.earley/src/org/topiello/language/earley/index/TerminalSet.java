package org.topiello.language.earley.index;

import org.topiello.text.TextUnit;

// TODO value type?
public class TerminalSet<T extends TextUnit>
    extends IndexedBitSet<IndexedLanguage<?, T>, IndexedTerminal<T>> {
  TerminalSet(IndexedLanguage<?, T> indexedRules) {
    super(indexedRules);
  }

  @Override
  protected int domainSize(IndexedLanguage<?, T> domain) {
    return domain.ruleCount();
  }

  @Override
  protected IndexedTerminal<T> get(IndexedLanguage<?, T> domain, int index) {
    return domain.terminal(index);
  }

  @Override
  protected int indexOf(IndexedLanguage<?, T> domain, IndexedTerminal<T> element) {
    return element.index();
  }
}
