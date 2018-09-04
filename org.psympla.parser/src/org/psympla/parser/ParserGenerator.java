package org.psympla.parser;

import org.psympla.language.Language;

public interface ParserGenerator<C> {
  <T> Parser<T, C> generate(Language<C> language);
}
