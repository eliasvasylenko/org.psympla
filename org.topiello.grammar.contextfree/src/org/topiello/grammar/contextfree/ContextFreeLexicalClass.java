package org.topiello.grammar.contextfree;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.lexicon.Variable;
import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class ContextFreeLexicalClass<C extends TextUnit> implements LexicalClass<Symbol, C> {
  private final Symbol variable;

  public ContextFreeLexicalClass(CharacterSet<C> characterSet, Symbol variable) {
    this.variable = variable;
  }

  public Symbol variable() {
    return variable;
  }

  @Override
  public Stream<Token<Symbol, C>> scan(Text<C> characters) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Token<Symbol, C>> print(Variable<Symbol> token) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return variable.toString();
  }
}
