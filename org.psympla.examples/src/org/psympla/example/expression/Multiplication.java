package org.psympla.example.expression;

import java.util.Collection;
import java.util.stream.Stream;

public class Multiplication implements Expression {
  public Multiplication(Collection<? extends Expression> factors) {
    // TODO Auto-generated constructor stub
  }

  public Multiplication(Expression... factors) {
    // TODO Auto-generated constructor stub
  }

  public Stream<Expression> factors() {
    throw new UnsupportedOperationException();
  }
}
