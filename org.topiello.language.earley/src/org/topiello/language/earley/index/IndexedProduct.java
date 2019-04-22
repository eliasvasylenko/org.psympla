package org.topiello.language.earley.index;

import org.topiello.grammar.Product;

//TODO value type & record
public class IndexedProduct<T> {
  private final LR0Item index;
  private final Product<T> product;

  public IndexedProduct(LR0Item item, Product<T> product) {
    this.index = item;
    this.product = product;
  }

  public LR0Item index() {
    return index;
  }

  public Product<T> product() {
    return product;
  }
}
