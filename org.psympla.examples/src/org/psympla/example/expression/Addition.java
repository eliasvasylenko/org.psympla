package org.psympla.example.expression;

import java.util.Collection;
import java.util.stream.Stream;

public class Addition implements Expression {
  public Addition(Collection<? extends Expression> addends) {
    // TODO Auto-generated constructor stub
  }

  public Addition(Expression... addends) {
    // TODO Auto-generated constructor stub
  }

  public Stream<Expression> addends() {
    throw new UnsupportedOperationException();
  }
}
