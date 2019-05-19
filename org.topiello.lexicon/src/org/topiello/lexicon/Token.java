package org.topiello.lexicon;

import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface Token<T, C extends TextUnit> {
  LexicalClass<T, C> lexicalClass();

  Text<C> lexeme();

  Variable<T> evaluate();
}
