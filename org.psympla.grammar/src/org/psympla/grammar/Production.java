package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.symbol.Cell;
import org.psympla.symbol.Pattern;

public class Production {
  private final List<Pattern<Cell>> production;

  public Production(Collection<? extends Pattern<Cell>> production) {
    this.production = new ArrayList<>(production);
  }

  public Stream<Pattern<Cell>> items() {
    return production.stream();
  }

  public int length() {
    return production.size();
  }

  public Pattern<Cell> item(int index) {
    return production.get(index);
  }
}
