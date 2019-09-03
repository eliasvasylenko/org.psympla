package org.topiello.scanner;

public class ScannerFailedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ScannerFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public ScannerFailedException(String message) {
    super(message);
  }

  public ScannerFailedException(Throwable cause) {
    super(cause);
  }

  protected ScannerFailedException() {}
}
