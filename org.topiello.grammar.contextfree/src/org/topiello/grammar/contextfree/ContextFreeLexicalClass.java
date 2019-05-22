package org.topiello.grammar.contextfree;

import java.util.Optional;
import java.util.stream.Stream;

import org.topiello.lexicon.LexicalClass;
import org.topiello.lexicon.Token;
import org.topiello.lexicon.Variable;
import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public abstract class ContextFreeLexicalClass<C extends TextUnit>
    implements LexicalClass<Symbol, C> {
  private final Symbol variable;

  public ContextFreeLexicalClass(CharacterSet<C> characterSet, Symbol variable) {
    this.variable = variable;
  }

  public Symbol variable() {
    return variable;
  }

  @Override
  public String toString() {
    return variable.toString();
  }

  public static class Regex<C extends TextUnit> extends ContextFreeLexicalClass<C> {
    public Regex(CharacterSet<C> characterSet, Symbol variable, String regex) {
      super(characterSet, variable);
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
  }

  public static class Literal<C extends TextUnit> extends ContextFreeLexicalClass<C> {
    public Literal(CharacterSet<C> characterSet, Symbol variable, String regex) {
      super(characterSet, variable);
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
  }
}
