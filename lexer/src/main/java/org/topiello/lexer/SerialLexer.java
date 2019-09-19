package org.topiello.lexer;

import java.util.Queue;
import java.util.Set;

import org.topiello.scanner.ScanWindow;
import org.topiello.scanner.Scanner;

public class SerialLexer<T, C, S> implements Lexer<T> {
  private final Scanner<C, S> scanner;
  private final ScanWindow<C, S> window;

  private long inputPosition;
  private T terminal;
  private Token<T, ?> token;
  private Queue<Token<T, ?>> tokenQueue;

  public SerialLexer(Scanner<C, S> scanner) {
    this.scanner = scanner;
    this.window = scanner.openWindow();
  }

  @Override
  public void advanceTo(long inputPosition) {
    scanner.advanceTo(inputPosition);
    window.discardToOffset(inputPosition);
  }

  @Override
  public boolean scan(T terminal) {
    return this.terminal == terminal;
  }

  @Override
  public Set<T> scan(Set<T> terminals) {
    if (terminals.contains(terminal)) {
      return Set.of(terminal);
    }
    return Set.of();
  }

  @Override
  public Token<T, ?> getToken(T terminal) {
    if (terminal.equals(this.terminal)) {
      return token;
    }
    return null;
  }
}
