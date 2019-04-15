package org.topiello.language.earley;

import org.osgi.service.component.annotations.Component;
import org.topiello.grammar.Grammar;
import org.topiello.grammar.Rule;
import org.topiello.language.Language;
import org.topiello.language.engine.LanguageEngine;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Semantics;
import org.topiello.text.TextUnit;

@Component
public class EarleyLanguageEngine implements LanguageEngine {
  @Override
  public <T extends Rule<?>, C extends TextUnit> Language<T, C> generate(
      Lexicon<T, C> lexicon,
      Grammar<T> grammar,
      Semantics semantics) {
    return new EarleyLanguage<>(lexicon, grammar, semantics);
  }
}
