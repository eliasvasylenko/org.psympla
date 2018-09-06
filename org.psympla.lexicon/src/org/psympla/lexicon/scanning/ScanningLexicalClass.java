package org.psympla.lexicon.scanning;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.symbol.Symbol;

public class ScanningLexicalClass<C> implements LexicalClass<C> {
  private final Symbol symbol;
  private final Scanner<C> scanner;

  protected ScanningLexicalClass(Symbol symbol, Scanner<C> scanner) {
    this.symbol = symbol;
    this.scanner = scanner;
  }

  @Override
  public Symbol symbol() {
    return symbol;
  }

  @Override
  public Stream<Lexeme<C>> scan(List<C> characters) {
    return scanner.scan(characters).map(scan -> new ScanningLexeme<C>(this, characters, scan));
  }

  @Override
  public Lexeme<C> print(Token token) {
    List<C> characters = scanner.print(token.value()).collect(toList());
    return new PrintingLexeme<>(this, characters, token);
  }
}
