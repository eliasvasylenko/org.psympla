package org.psympla.lexicon;

import java.util.List;
import java.util.function.Function;

import org.psympla.grammar.Symbol;
import org.psympla.grammar.Term;

public class LexicalClass<C> {
  private final Symbol symbol;
  private final Function<List<C>, Scan<C>> evaluator;

  public LexicalClass(Symbol symbol, Function<List<C>, Scan<C>> evaluator) {
    this.symbol = symbol;
    this.evaluator = evaluator;
  }

  public Symbol symbol() {
    return symbol;
  }

  public Lexeme<C> scan(List<C> characters) {
    var scan = evaluator.apply(characters);
    return new Lexeme<C>(
        characters.subList(0, scan.length()),
        new Token(new Term(symbol(), scan.parameters())));
  }
}
