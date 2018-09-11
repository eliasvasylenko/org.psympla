package org.psympla.parser.earley;

import org.osgi.service.component.annotations.Component;
import org.psympla.grammar.Grammar;
import org.psympla.language.Language;
import org.psympla.language.LanguageEngine;
import org.psympla.lexicon.Lexicon;
import org.psympla.semantics.Semantics;

@Component
public class EarleyLanguageEngine<C> implements LanguageEngine<C> {
  @Override
  public Language<C> generate(Lexicon<C> lexicon, Grammar grammar, Semantics semantics) {
    return new EarleyLanguage<>(lexicon, grammar, semantics);
  }
}
