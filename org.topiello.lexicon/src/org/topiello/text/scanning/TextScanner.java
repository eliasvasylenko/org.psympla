package org.topiello.text.scanning;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.sound.midi.Sequence;

import org.topiello.lexicon.scanning.Scan;
import org.topiello.lexicon.scanning.Scanner;
import org.topiello.symbol.Cell;
import org.topiello.symbol.Nil;
import org.topiello.text.CharacterSet;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public class TextScanner<C extends TextUnit> implements Scanner<C, Cell<Value<String>, Nil>> {
  private final CharacterSet<C> characterSet;
  private final Function<? super Text<C>, ? extends IntStream> scan;

  public TextScanner(
      CharacterSet<C> characterSet,
      Function<? super Text<C>, ? extends IntStream> scan) {
    this.characterSet = characterSet;
    this.scan = scan;
  }

  @Override
  public Stream<Scan<Cell<Value<String>, Nil>>> scan(Text<C> characters) {
    return scan
        .apply(characters)
        .mapToObj(
            i -> Scan
                .forParameter(
                    i,
                    new Value<>(characterSet.toChars(characters.subSequence(i)).toString())
                        .consOnto(Sequence.empty())));
  }
}
