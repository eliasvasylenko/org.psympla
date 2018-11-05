package org.psympla.symbol;

import java.util.stream.Stream;

public class Nil implements Sequence, Atom<Void> {
  private static final Nil INSTANCE = new Nil();

  private Nil() {}

  public static Nil instance() {
    return INSTANCE;
  }

  @Override
  public Stream<LexicalItem> elements() {
    return Stream.empty();
  }

  @Override
  public boolean isProper() {
    return true;
  }
}
