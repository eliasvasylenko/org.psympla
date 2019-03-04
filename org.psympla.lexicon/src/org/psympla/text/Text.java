package org.psympla.text;

import java.util.Optional;

import org.psympla.lexicon.Characters;

public class Text<C extends TextUnit> implements Characters<C> {
  private final String string;

  public Text(String string) {
    this.string = string;
  }

  public Text(Characters<C> characters) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Text<C> subSequence(int from, int to) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return string;
  }

  public Optional<Integer> match(String regex) {
    throw new UnsupportedOperationException();
  }

  public boolean startsWith(String literal) {
    throw new UnsupportedOperationException();
  }
}
