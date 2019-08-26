package org.topiello.scanner.bytes;

public class ScannerInterruptedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ScannerInterruptedException(InterruptedException e) {
    super(e);
  }
}
