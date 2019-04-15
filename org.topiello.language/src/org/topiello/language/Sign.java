package org.topiello.language;

import org.topiello.text.Text;

/**
 * Within the context of a {@link Language}, a sign has two components, the
 * signifier {@link Text text}, and the signified meaning. The signified meaning
 * in this case is represented by a Java Object of Type {@code T}. A signifier
 * which belongs to a language is encoded from, and decoded to, its meaning
 * according to a {@link Designation}.
 * 
 * @author Elias N Vasylenko
 *
 * @param <T>
 *          the type of the Java representation of the signified meaning
 */
public final class Sign<T> {}
