package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.pattern.Pattern;
import org.psympla.symbol.LexicalItem;

public class Production {
  private final List<Pattern<?>> production;

  @SafeVarargs
  public Production(Pattern<?>... production) {
    this(Arrays.asList(production));
  }

  public Production(Collection<? extends Pattern<?>> production) {
    this.production = new ArrayList<>(production);
  }

  public Stream<Pattern<?>> items() {
    return production.stream();
  }

  public int length() {
    return production.size();
  }

  public Pattern<? extends LexicalItem> item(int index) {
    return production.get(index);
  }
}
