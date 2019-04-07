package org.topiello.lexicon;

import org.topiello.symbol.Sequence;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface Lexeme<C extends TextUnit, T extends Sequence> {
  LexicalClass<C, T> lexicalClass();

  Text<C> characters();

  Token<T> evaluate();
}
