package org.topiello.scanner.bytes.channel;

import org.topiello.scanner.bytes.concurrent.ScannerFailedException;

public class NonBlockingChannelException extends ScannerFailedException {
  private static final long serialVersionUID = 1L;

  public NonBlockingChannelException() {
    super("Channel cannot be non-blocking");
  }
}
