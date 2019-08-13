package org.topiello.scanner;

public class InputPositionOutOfBoundsException extends IndexOutOfBoundsException {
  private static final long serialVersionUID = 1L;

  public InputPositionOutOfBoundsException(long inputPosition) {
    super(Long.toString(inputPosition));
  }
}
