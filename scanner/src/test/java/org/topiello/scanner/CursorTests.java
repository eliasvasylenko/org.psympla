package org.topiello.scanner;

import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CursorTests {
  @Test
  void shouldProduceEmptyCursor_whenInvokeDefaultConstructor() {
    var cursor = new Cursor<>();

    Assertions.assertTrue(cursor.isEndOfInput());
  }

  @Test
  void shouldProduceNonEmptyCursor_whenUseNonEmptyConstructor() {
    var object = new Object();

    var cursor = new Cursor<>(object);

    Assertions.assertFalse(cursor.isEndOfInput());
  }

  @Test
  void shouldProduceValue_whenGetOrElseOnNonEmptyCursor() {
    var object = new Object();

    var cursor = new Cursor<>(object);

    Assertions.assertSame(object, cursor.characterOrElse(null));
  }

  @Test
  void shouldProduceValue_whenGetOrThrowOnNonEmptyCursor() {
    var object = new Object();

    var cursor = new Cursor<>(object);

    Assertions.assertSame(object, cursor.characterOrThrow(RuntimeException::new));
  }

  @Test
  void shouldMatchPredicate_whenTestedAgainstCursorWithMatchingValue() {
    var object = new Object();

    var cursor = new Cursor<>(object);

    Assertions.assertTrue(cursor.characterMatches(object::equals));
  }

  @Test
  void shouldFailPredicate_whenTestedAgainstCursorWithNonMatchingValue() {
    var object = new Object();

    var cursor = new Cursor<>(object);

    Assertions.assertFalse(cursor.characterMatches(Predicate.not(object::equals)));
  }

  @Test
  void shouldGiveDefaultValue_whenGetOrElseOnEmptyCursor() {
    var cursor = new Cursor<Object>();

    var defaultObject = new Object();

    Assertions.assertEquals(defaultObject, cursor.characterOrElse(defaultObject));
  }

  @Test
  void shouldThrowGivenThrowable_whenGetOrThrowOnEmptyCursor() {
    var cursor = new Cursor<>();

    var throwable = new Throwable() {
      private static final long serialVersionUID = 1L;
    };

    Assertions.assertThrows(throwable.getClass(), () -> cursor.characterOrThrow(() -> throwable));
  }

  @Test
  void shouldFailPredicate_whenTestedAgainstEmptyCursor() {
    var cursor = new Cursor<>();

    Assertions.assertFalse(cursor.characterMatches(c -> true));
  }
}
