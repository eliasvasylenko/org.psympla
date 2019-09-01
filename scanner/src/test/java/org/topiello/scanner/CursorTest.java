package org.topiello.scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CursorTest {
  @Test
  void testDefaultConstructorProducesEmptyCursor() {
    var cursor = new Cursor<>();

    Assertions.assertTrue(cursor.isEndOfInput());
  }

  @Test
  void testEmptyCursorGetsDefault() {
    var cursor = new Cursor<Object>();

    var defaultObject = new Object();

    Assertions.assertEquals(defaultObject, cursor.characterOrElse(defaultObject));
  }

  @Test
  void testEmptyCursorThrowsOnGet() {
    var cursor = new Cursor<>();

    var throwable = new Throwable() {
      private static final long serialVersionUID = 1L;
    };

    Assertions.assertThrows(throwable.getClass(), () -> cursor.characterOrThrow(() -> throwable));
  }

  @Test
  void testEmptyCursorDoesNotMachPredicate() {
    var cursor = new Cursor<>();

    Assertions.assertFalse(cursor.characterMatches(c -> true));
  }
}
