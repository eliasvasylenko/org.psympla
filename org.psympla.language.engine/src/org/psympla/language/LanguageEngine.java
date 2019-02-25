package org.psympla.language;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;
import org.psympla.semantics.Semantics;

public interface LanguageEngine {
  <C> Language<C> generate(Lexicon<C> lexicon, Grammar grammar, Semantics semantics);
}
