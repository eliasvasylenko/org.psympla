package org.topiello.text;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * 
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class CharacterSet<C extends TextUnit> {
  private final Function<? super Text<C>, ? extends CharSequence> toChars;
  private final Function<? super CharSequence, ? extends Text<C>> fromChars;
  private final Map<CharacterSet<?>, CharacterConverter<C, ?>> converters = new HashMap<>();

  public CharacterSet(
      Function<? super Text<C>, ? extends CharSequence> toChars,
      Function<? super CharSequence, ? extends Text<C>> fromChars,
      Collection<? extends CharacterConverter<C, ?>> converters) {
    this.toChars = toChars;
    this.fromChars = fromChars;
    for (var converter : converters) {
      this.converters.put(converter.characterSet(), converter);
    }
  }

  @SafeVarargs
  public CharacterSet(
      Function<? super Text<C>, ? extends CharSequence> toChars,
      Function<? super CharSequence, ? extends Text<C>> fromChars,
      CharacterConverter<C, ?>... converters) {
    this(toChars, fromChars, Arrays.asList(converters));
  }

  @SuppressWarnings("unchecked")
  public <D extends TextUnit> Optional<CharacterConverter<C, D>> converting(
      CharacterSet<D> characterSet) {
    return Optional.ofNullable((CharacterConverter<C, D>) converters.get(characterSet));
  }

  public CharSequence toChars(Text<C> text) {
    return toChars.apply(text);
  }

  public Text<C> fromChars(CharSequence string) {
    return fromChars.apply(string);
  }
}
