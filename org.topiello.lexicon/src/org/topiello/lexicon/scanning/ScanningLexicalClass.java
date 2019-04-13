package org.topiello.lexicon.scanning;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class ScanningLexicalClass<T, C extends TextUnit> implements LexicalClass<T, C> {
  private final T variable;
  private final Scanner<T, C> scanner;
  private final Printer<T, C> printer;

  public ScanningLexicalClass(T variable, Scanner<T, C> scanner, Printer<T, C> printer) {
    this.variable = variable;
    this.scanner = scanner;
    this.printer = printer;
  }

  public T variable() {
    return variable;
  }

  @Override
  public Stream<Lexeme<T, C>> scan(Text<C> characters) {
    return scanner.scan(characters).map(scan -> new ScanningLexeme<>(this, characters, scan));
  }

  @Override
  public Optional<Lexeme<T, C>> print(Token<T> token) {
    return printer.print(token.value()).map(text -> new PrintingLexeme<>(this, text, token));
  }

  @Override
  public String toString() {
    return variable + " -> " + scanner + " <- " + printer;
  }
}
