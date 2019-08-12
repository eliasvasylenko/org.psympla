package org.topiello.scanner;

import java.util.function.Predicate;
import java.util.function.Supplier;

// TODO inline class
public class Cursor<C> {
  private final boolean endOfInput;
  private final C character;

  public Cursor() {
    this.endOfInput = true;
    this.character = null; // TODO C.default;
  }

  public Cursor(C character) {
    this.endOfInput = false;
    this.character = character;
  }

  public boolean isEndOfInput() {
    return endOfInput;
  }

  public C characterOrElse(C defaultCharacter) {
    return endOfInput ? defaultCharacter : character;
  }

  public <E extends Throwable> C characterOrThrow(Supplier<E> throwable) throws E {
    if (endOfInput) {
      throw throwable.get();
    }
    return character;
  }

  public boolean characterMatches(Predicate<C> condition) {
    return !endOfInput && condition.test(character);
  }
}
