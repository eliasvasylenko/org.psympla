package org.psympla.lexicon.scanning;

import java.util.Optional;
import java.util.stream.Stream;

import org.psympla.constraint.Scope;
import org.psympla.lexicon.Lexeme;
import org.psympla.lexicon.LexicalClass;
import org.psympla.lexicon.Token;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public class ScanningLexicalClass<C extends TextUnit, T extends Sequence>
    implements LexicalClass<C, T> {
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
  public Stream<Lexeme<C, T>> scan(Text<C> characters) {
    return scanner.scan(characters).map(scan -> new ScanningLexeme<C, T>(this, characters, scan));
  }

  @Override
  public Optional<Lexeme<C, T>> print(Token<T> token) {
    return printer.print(token.value()).map(text -> new PrintingLexeme<>(this, text, token));
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
