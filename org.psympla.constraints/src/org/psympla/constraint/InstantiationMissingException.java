package org.psympla.constraint;

import org.psympla.pattern.Variable;

public class InstantiationMissingException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InstantiationMissingException(Variable variable) {
    super("Instantiation missing for variable " + variable);
  }
}
