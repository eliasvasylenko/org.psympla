package org.topiello.language.engine;

import org.topiello.grammar.Grammar;
import org.topiello.grammar.Rule;
import org.topiello.language.Language;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Semantics;
import org.topiello.text.TextUnit;

public interface LanguageEngine {
  <T extends Rule<?>, C extends TextUnit> Language<T, C> generate(
      Lexicon<T, C> lexicon,
      Grammar<T> grammar,
      Semantics semantics);
}
