package org.topiello.ast;

public interface TopielloNodeFactory {
  BooleanNode not(BooleanNode node);

  TopielloNode conditional(BooleanNode condition, TopielloNode ifThen);

  TopielloNode conditional(BooleanNode condition, TopielloNode ifThen, TopielloNode elseThen);
}
