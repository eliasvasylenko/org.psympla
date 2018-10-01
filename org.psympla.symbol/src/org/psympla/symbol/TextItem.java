package org.psympla.symbol;

public class TextItem implements Atom<TextItem> {
  private final String text;

  public TextItem(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
