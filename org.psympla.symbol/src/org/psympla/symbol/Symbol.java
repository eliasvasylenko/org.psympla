package org.psympla.symbol;

import java.util.Objects;

/**
 * A symbol.
 * 
 * @author Elias N Vasylenko
 */
public class Symbol implements LexicalItem<Symbol> {
  private final String namespace;
  private final String id;

  public Symbol(String namespace, String id) {
    this.namespace = namespace;
    this.id = id;
  }

  /**
   * The namespace of the symbol. In practice users may choose a string
   * representation of some domain specific concept of scope such as a Java
   * package or a URI. As such there are no built-in restrictions on format so as
   * to be permissive of as many schemes as possible. Typically such concepts have
   * a straightforward and canonical string format, so this is expected to be a
   * flexible abstraction.
   * 
   * @return a string representation of the symbol's namespace
   */
  public String getNamespace() {
    return namespace;
  }

  /**
   * @return the id of the symbol
   */
  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + namespace + " " + id + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof Symbol))
      return false;

    Symbol that = (Symbol) obj;

    return Objects.equals(this.namespace, that.namespace) && Objects.equals(this.id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(namespace, id);
  }
}
