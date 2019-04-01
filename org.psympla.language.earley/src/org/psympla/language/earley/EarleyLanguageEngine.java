package org.psympla.language.earley;

import org.osgi.service.component.annotations.Component;
import org.psympla.grammar.Grammar;
import org.psympla.language.Language;
import org.psympla.language.engine.LanguageEngine;
import org.psympla.lexicon.Lexicon;
import org.psympla.semantics.Semantics;
import org.psympla.text.TextUnit;

@Component
public class EarleyLanguageEngine implements LanguageEngine {
  @Override
  public <C extends TextUnit> Language<C> generate(
      Lexicon<C> lexicon,
      Grammar grammar,
      Semantics semantics) {
    return new EarleyLanguage<>(lexicon, grammar, semantics);
  }
}
