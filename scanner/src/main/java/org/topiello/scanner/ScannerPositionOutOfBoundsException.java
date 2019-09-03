package org.topiello.scanner;

public class ScannerPositionOutOfBoundsException extends IndexOutOfBoundsException {
  private static final long serialVersionUID = 1L;

  public ScannerPositionOutOfBoundsException(long inputPosition) {
    super(Long.toString(inputPosition));
  }
}
