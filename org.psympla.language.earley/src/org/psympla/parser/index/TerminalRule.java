package org.psympla.parser.index;

import java.util.List;

import org.psympla.constraint.ValueType;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.Characters;
import org.psympla.lexicon.LexicalClass;
import org.psympla.pattern.Patterns;
import org.psympla.pattern.Variable;

public class TerminalRule<C> extends Rule {
  private static final Variable LEXEME = Patterns.variable("T");

  private final LexicalClass<C, ?> lexicalClass;

  public TerminalRule(LexicalClass<C, ?> lexicalClass) {
    super(
        lexicalClass.pattern(),
        List.of(LEXEME),
        lexicalClass.scope().withConstraint(new ValueType<>(LEXEME, String.class)));
    this.lexicalClass = lexicalClass;
  }

  public LexicalClass<C, ?> lexicalClass() {
    return lexicalClass;
  }

  public boolean isNullable() {
    return lexicalClass.scan(Characters.empty()).findAny().isEmpty();
  }
}
