package org.topiello.language.earley.index;

import java.util.List;

import org.topiello.constraint.ValueType;
import org.topiello.grammar.Rule;
import org.topiello.lexicon.LexicalClass;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

/**
 * A synthetic rule representing a lexical class. The LHS is the terminal symbol
 * which matches the lexical class, and the RHS is a variable intended to be
 * instantiated to the input text which is successfully parsed.
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class TerminalRule<T extends Rule<?>, C extends TextUnit> extends IndexedRule<T> {
  private final LexicalClass<C, ?> lexicalClass;
  private final List<IndexedItem> items;

  TerminalRule(int index, IndexedLanguage<T, C> indexedLanguage, LexicalClass<C, ?> lexicalClass) {
    super(
        index,
        indexedLanguage,
        lexicalClass.pattern(),
        lexicalClass.scope().withConstraint(new ValueType<>(LEXEME, String.class)),
        List.of(LEXEME));
    this.lexicalClass = lexicalClass;
    this.items = List.of(IndexedItem.terminal(this), IndexedItem.complete(this));
  }

  @Override
  protected List<IndexedItem> getItems() {
    return items;
  }

  public LexicalClass<C, ?> lexicalClass() {
    return lexicalClass;
  }

  public boolean isNullable() {
    return lexicalClass.scan(Text.empty()).findAny().isEmpty();
  }
}
