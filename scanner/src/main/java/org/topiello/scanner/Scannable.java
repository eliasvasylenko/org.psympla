package org.topiello.scanner;

import java.util.concurrent.Executor;

public interface Scannable<C, T> {
  Scanner<C, T> openScanner(Executor executor);
}
