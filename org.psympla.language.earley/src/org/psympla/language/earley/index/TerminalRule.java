package org.psympla.language.earley.index;

import java.util.List;

import org.psympla.constraint.ValueType;
import org.psympla.lexicon.Characters;
import org.psympla.lexicon.LexicalClass;
import org.psympla.pattern.Patterns;
import org.psympla.pattern.Variable;

/**
 * A synthetic rule representing a lexical class. The LHS is the terminal symbol
 * which matches the lexical class, and the RHS is a variable intended to be
 * instantiated to the input text which is successfully parsed.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class TerminalRule<C> extends IndexedRule {
  private static final Variable LEXEME = Patterns.variable("T");

  private final TerminalRuleIndex<C> indexed;
  private final LexicalClass<C, ?> lexicalClass;

  public TerminalRule(int index, TerminalRuleIndex<C> indexed, LexicalClass<C, ?> lexicalClass) {
    super(index
        lexicalClass.pattern(),
        List.of(LEXEME),
        lexicalClass.scope().withConstraint(new ValueType<>(LEXEME, String.class)));
    this.indexed = indexed;
    this.index = indexed.count();
    this.lexicalClass = lexicalClass;
  }

  public TerminalRuleIndex<C> indexed() {
    return indexed;
  }

  public LexicalClass<C, ?> lexicalClass() {
    return lexicalClass;
  }

  public boolean isNullable() {
    return lexicalClass.scan(Characters.empty()).findAny().isEmpty();
  }
}
