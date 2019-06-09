/*
 * Topiello Text - The text API
 * Copyright © 2018 Strange Skies (elias@vasylenko.uk)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
