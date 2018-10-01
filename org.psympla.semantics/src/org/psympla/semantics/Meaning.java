package org.psympla.semantics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.symbol.Pattern;

public class Meaning<T> {
  private final Sign<? super T> sign;
  private final Class<T> type;
  private final List<Pattern<?>> phraseParts;

  public Meaning(Sign<? super T> sign, Class<T> type, Pattern<?>... phraseParts) {
    this(sign, type, Arrays.asList(phraseParts));
  }

  public Meaning(
      Sign<? super T> sign,
      Class<T> type,
      Collection<? extends Pattern<?>> phraseParts) {
    this.sign = sign;
    this.type = type;
    this.phraseParts = new ArrayList<>(phraseParts);
  }

  public Sign<? super T> sign() {
    return sign;
  }

  public Class<T> type() {
    return type;
  }

  public Stream<Pattern<?>> getPhraseParts() {
    return phraseParts.stream();
  }
}
