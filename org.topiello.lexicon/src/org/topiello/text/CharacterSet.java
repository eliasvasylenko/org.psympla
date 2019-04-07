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
  private final Map<CharacterSet<?>, CharacterConverter<C, ?>> converters = new HashMap<>();

  public CharacterSet(
      Function<Text<C>, String> toString,
      Function<String, Text<C>> fromString,
      Collection<? extends CharacterConverter<C, ?>> converters) {
    for (var converter : converters) {
      this.converters.put(converter.characterSet(), converter);
    }
  }

  @SafeVarargs
  public CharacterSet(
      Function<Text<C>, String> toString,
      Function<String, Text<C>> fromString,
      CharacterConverter<C, ?>... converters) {
    this(toString, fromString, Arrays.asList(converters));
  }

  @SuppressWarnings("unchecked")
  public <D extends TextUnit> Optional<CharacterConverter<C, D>> converting(
      CharacterSet<D> characterSet) {
    return Optional.ofNullable((CharacterConverter<C, D>) converters.get(characterSet));
  }

  public String toString(Text<C> text) {
    return null;
  }

  public Text<C> fromString(String string) {
    return null;
  }
}
