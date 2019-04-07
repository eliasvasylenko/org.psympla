package org.topiello.text;

import java.util.Iterator;
import java.util.function.Function;

public abstract class CharacterConverter<C extends TextUnit, D extends TextUnit> {
  private final CharacterSet<D> characterSet;

  public CharacterConverter(CharacterSet<D> characterSet) {
    this.characterSet = characterSet;
  }

  public CharacterSet<D> characterSet() {
    return characterSet;
  }

  public abstract Iterator<D> dissemble(C character);

  public abstract C assemble(Iterator<D> characters);

  public static <C extends TextUnit, D extends TextUnit> CharacterConverter<C, D> toAndFrom(
      CharacterSet<D> characterSet,
      Function<C, Iterator<D>> dissembleTo,
      Function<Iterator<D>, C> assembleFrom) {
    return new CharacterConverter<>(characterSet) {
      @Override
      public Iterator<D> dissemble(C character) {
        return dissembleTo.apply(character);
      }

      @Override
      public C assemble(Iterator<D> characters) {
        return assembleFrom.apply(characters);
      }
    };
  }
}
