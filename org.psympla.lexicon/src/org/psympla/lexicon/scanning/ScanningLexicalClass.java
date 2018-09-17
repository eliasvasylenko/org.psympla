package org.psympla.lexicon.scanning;

import java.util.stream.Stream;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Sequence;
import org.psympla.lexicon.Token;
import org.psympla.symbol.Symbol;

public class ScanningLexicalClass<C> implements LexicalClass<C> {
  private final Symbol symbol;
  private final Scanner<C> scanner;
  private final Printer<C> printer;

  public ScanningLexicalClass(Symbol symbol, Scanner<C> scanner, Printer<C> printer) {
    this.symbol = symbol;
    this.scanner = scanner;
    this.printer = printer;
  }

  @Override
  public Symbol symbol() {
    return symbol;
  }

  @Override
  public Stream<Lexeme<C>> scan(Sequence<C> characters) {
    return scanner.scan(characters).map(scan -> new ScanningLexeme<C>(this, characters, scan));
  }

  @Override
  public Lexeme<C> print(Token token) {
    Sequence<C> characters = printer.print(token.value());
    return new PrintingLexeme<>(this, characters, token);
  }
}
