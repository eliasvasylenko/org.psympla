package org.topiello.scanner.bytes;

public interface BlockFeeder {
  public void advance(long inputPosition);

  public InputBlock open();

  public void close(InputBlock block);
}
