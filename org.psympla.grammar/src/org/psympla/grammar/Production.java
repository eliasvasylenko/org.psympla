package org.psympla.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Pattern;

public class Production {
  private final List<Pattern<? extends LexicalItem<?>>> production;

  @SafeVarargs
  public Production(Pattern<? extends LexicalItem<?>>... production) {
    this(Arrays.asList(production));
  }

  public Production(Collection<? extends Pattern<? extends LexicalItem<?>>> production) {
    this.production = new ArrayList<>(production);
  }

  public Stream<Pattern<? extends LexicalItem<?>>> items() {
    return production.stream();
  }

  public int length() {
    return production.size();
  }

  public Pattern<? extends LexicalItem<?>> item(int index) {
    return production.get(index);
  }
}
