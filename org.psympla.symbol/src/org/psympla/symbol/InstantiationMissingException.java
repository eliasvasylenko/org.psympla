package org.psympla.symbol;

public class InstantiationMissingException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InstantiationMissingException(Variable<?> variable) {
    super("Instantiation missing for variable " + variable);
  }
}
