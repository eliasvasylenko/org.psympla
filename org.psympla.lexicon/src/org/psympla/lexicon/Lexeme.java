package org.psympla.lexicon;

import org.psympla.symbol.Sequence;
import org.psympla.text.Text;
import org.psympla.text.TextUnit;

public interface Lexeme<C extends TextUnit, T extends Sequence> {
  LexicalClass<C, T> lexicalClass();

  Text<C> characters();

  Token<T> evaluate();
}
