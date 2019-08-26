package org.topiello.scanner.bytes.concurrent;

public class ScannerFailedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ScannerFailedException(Exception e) {
    super(e);
  }
}
