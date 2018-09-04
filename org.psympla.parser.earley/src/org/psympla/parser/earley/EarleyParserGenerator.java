package org.psympla.parser.earley;

import org.psympla.grammar.Grammar;
import org.psympla.lexicon.Lexicon;
import org.psympla.parser.Parser;
import org.psympla.parser.ParserGenerator;

public class EarleyParserGenerator<C> implements ParserGenerator<C> {
  public EarleyParserGenerator(Grammar grammar, Lexicon<C> lexicon) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public <T> Parser<T, C> generate(Grammar grammar, Lexicon<C> lexicon) {
    // TODO Auto-generated method stub
    return null;
  }
}
