package org.psympla.language.earley.index;

import static java.util.stream.Collectors.toList;

import org.psympla.grammar.Rule;

/**
 * A synthetic rule representing a lexical class. The LHS is the terminal symbol
 * which matches the lexical class, and the RHS is a variable intended to be
 * instantiated to the input text which is successfully parsed.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
// TODO value type?
public class NonterminalRule extends IndexedRule {
  NonterminalRule(int index, IndexedLanguage<?> indexedLanguage, Rule rule) {
    super(index, indexedLanguage, rule.pattern(), rule.products().collect(toList()), rule.scope());
  }
}
