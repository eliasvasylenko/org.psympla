package org.topiello.lexicon.scanning;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.lexicon.Lexeme;
import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface ScanningLexicalClass<T extends Token<?, ?>, C extends TextUnit>
    extends LexicalClass<T, C> {
  Scanner<T, C> scanner();

  Printer<T, C> printer();

  @Override
  default Stream<Lexeme<T, C>> scan(Text<C> characters) {
    return scanner().scan(characters).map(scan -> new ScanningLexeme<>(this, characters, scan));
  }

  @Override
  default Optional<Lexeme<T, C>> print(T token) {
    return printer().print(token).map(text -> new PrintingLexeme<>(this, text, token));
  }
}
