package org.topiello.scanner;

public class EndOfInputException extends ScannerPositionOutOfBoundsException {
  private static final long serialVersionUID = 1L;

  public EndOfInputException(long inputPosition) {
    super(inputPosition);
  }
}
