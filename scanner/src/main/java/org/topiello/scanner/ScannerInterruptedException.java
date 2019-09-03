package org.topiello.scanner;

public class ScannerInterruptedException extends ScannerFailedException {
  private static final long serialVersionUID = 1L;

  public ScannerInterruptedException(InterruptedException e) {
    super(e);
  }
}
