package org.psympla.lexicon.scanning;

import java.util.stream.Stream;

import org.psympla.constraint.Scope;
import org.psympla.lexicon.Characters;
import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;

public class ScanningLexicalClass<C, T extends Sequence> implements LexicalClass<C, T> {
  private final Symbol symbol;
  private final Scanner<C, T> scanner;
  private final Printer<C, T> printer;

  public ScanningLexicalClass(Symbol symbol, Scanner<C, T> scanner, Printer<C, T> printer) {
    this.symbol = symbol;
    this.scanner = scanner;
    this.printer = printer;
  }

  @Override
  public Symbol symbol() {
    return symbol;
  }

  @Override
  public Stream<Lexeme<C, T>> scan(Characters<C> characters) {
    return scanner.scan(characters).map(scan -> new ScanningLexeme<C, T>(this, characters, scan));
  }

  @Override
  public Lexeme<C, T> print(Token<T> token) {
    Characters<C> characters = printer.print(token.value());
    return new PrintingLexeme<>(this, characters, token);
  }

  @Override
  public Pattern parameter() {
    return Patterns.variable("T");
  }

  @Override
  public String toString() {
    return Patterns.cons(symbol(), parameter()) + " -> " + scanner + " <- " + printer;
  }

  @Override
  public Scope scope() {
    return Scope.empty();
  }
}
