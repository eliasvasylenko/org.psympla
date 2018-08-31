package uk.co.strangeskies.modabi.parser;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.psympla.grammar.Pattern;
import org.psympla.grammar.Symbol;

public class Behaviour<T> {
  public Behaviour(
      Pattern<Symbol> symbol,
      Function<SymbolsIn, T> input,
      BiConsumer<SymbolsOut, T> output) {
    // TODO Auto-generated constructor stub
  }
}
