package org.psympla.grammar;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Sequence implements LexicalItem<Sequence> {
  private static final Sequence EMPTY = new Sequence(emptyList());

  private final List<LexicalItem<?>> elements;

  public Sequence(Collection<? extends LexicalItem<?>> elements) {
    this.elements = new ArrayList<>(elements);
  }

  public Stream<LexicalItem<?>> getElements() {
    return elements.stream();
  }

  static Sequence empty() {
    return EMPTY;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + getElements().map(Objects::toString).collect(joining("(", " ", ")"));
  }
}
