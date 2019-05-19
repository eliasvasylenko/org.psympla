package org.topiello.lexicon.scanning;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.lexicon.Variable;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface ScanningLexicalClass<T, C extends TextUnit> extends LexicalClass<T, C> {
  Scanner<T, C> scanner();

  Printer<T, C> printer();

  @Override
  default Stream<Token<T, C>> scan(Text<C> characters) {
    return scanner().scan(characters).map(scan -> new ScanningLexeme<>(this, characters, scan));
  }

  @Override
  default Optional<Token<T, C>> print(Variable<T> token) {
    return printer().print(token.variable()).map(text -> new PrintingLexeme<>(this, text, token));
  }
}
