package org.psympla.semantics;

import static java.util.Collections.singleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Semantics {
  private final List<Denotation<?>> denotations;

  protected Semantics(Collection<? extends Denotation<?>> denotations) {
    this.denotations = new ArrayList<>(denotations);
  }

  private Semantics(List<Denotation<?>> denotations) {
    this.denotations = denotations;
  }

  public Semantics withDenotation(Denotation<?> denotations) {
    return withDenotations(singleton(denotations));
  }

  public Semantics withDenotations(Collection<? extends Denotation<?>> denotations) {
    var newDenotations = new ArrayList<Denotation<?>>(this.denotations.size() + denotations.size());
    newDenotations.addAll(this.denotations);
    denotations.forEach(newDenotations::add);
    return new Semantics(newDenotations);
  }
}
