package org.psympla.parser.index;

import java.util.stream.Stream;

import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Lexicon;
import org.psympla.pattern.Pattern;

public class LexicalIndex<C> extends Index<LexicalClass<C, ?>> {
  private final Lexicon<C> lexicon;

  public LexicalIndex(Lexicon<C> lexicon) {
    super(lexicon::getLexicalClasses, LexicalClass::pattern);
    this.lexicon = lexicon;
  }

  public Lexicon<C> getLexicon() {
    return lexicon;
  }

  public Stream<LexicalClass<C, ?>> getLexicalGroups(Pattern pattern) {
    return getItems(pattern);
  }
}
