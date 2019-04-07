package org.topiello.language.earley;

import org.osgi.service.component.annotations.Component;
import org.topiello.grammar.Grammar;
import org.topiello.language.Language;
import org.topiello.language.engine.LanguageEngine;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Semantics;
import org.topiello.text.TextUnit;

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
