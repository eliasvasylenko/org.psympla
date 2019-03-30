package org.psympla.lexicon;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 
 * 
 * @author Elias N Vasylenko
 *
 * @param <C>
 */
public class CharacterSet<C> {
  public static final CharacterSet<Byte> BYTES = new CharacterSet<>();

  private final Map<CharacterSet<?>, CharacterConverter<C, ?>> converters = new HashMap<>();

  public CharacterSet(Collection<? extends CharacterConverter<C, ?>> converters) {
    for (var converter : converters) {
      this.converters.put(converter.characterSet(), converter);
    }
  }

  @SafeVarargs
  public CharacterSet(CharacterConverter<C, ?>... converters) {
    this(Arrays.asList(converters));
  }

  @SuppressWarnings("unchecked")
  <D> Optional<CharacterConverter<C, D>> converting(CharacterSet<D> characterSet) {
    return Optional.ofNullable((CharacterConverter<C, D>) converters.get(characterSet));
  }
}
