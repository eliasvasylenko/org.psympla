package org.topiello.language.engine;

import org.topiello.grammar.Grammar;
import org.topiello.language.Language;
import org.topiello.lexicon.Lexicon;
import org.topiello.semantics.Semantics;
import org.topiello.text.TextUnit;

public interface LanguageEngine {
  <C extends TextUnit> Language<C> generate(
      Lexicon<C> lexicon,
      Grammar grammar,
      Semantics semantics);
}
