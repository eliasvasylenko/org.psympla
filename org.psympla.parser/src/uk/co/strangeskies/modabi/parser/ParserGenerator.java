package uk.co.strangeskies.modabi.parser;

import org.psympla.grammar.Sequence;

public interface ParserGenerator {
  <T> Parser<T> forSymbol(Sequence expression);
}
