package org.topiello.ast.index;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;

import org.topiello.grammar.Product;

//TODO value type & record
public class LR0Item {
  private static final String ARROW_STRING = " -> ";
  private static final String DOT_STRING = " . ";
  private static final String COLON_STRING = " : ";

  private final IndexedRule<?> rule;
  private final int dotPosition;

  public LR0Item(IndexedRule<?> rule, int dotPosition) {
    this.rule = rule;
    this.dotPosition = dotPosition;
  }

  public IndexedRule<?> rule() {
    return rule;
  }

  public int dotPosition() {
    return dotPosition;
  }

  public boolean isComplete() {
    return rule.productCount() == dotPosition;
  }

  public Optional<Product> nextProduct() {
    return isComplete() ? Optional.empty() : Optional.of(rule.product(dotPosition));
  }

  public Optional<LR0Item> nextItem() {
    return isComplete() ? Optional.empty() : Optional.of(new LR0Item(rule, dotPosition + 1));
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != getClass())
      return false;

    LR0Item that = (LR0Item) obj;

    return Objects.equals(this.rule, that.rule) && (this.dotPosition == that.dotPosition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rule, dotPosition);
  }

  @Override
  public String toString() {
    return rule.rule() + ARROW_STRING + rule.products().limit(dotPosition).collect(productString())
        + DOT_STRING + rule.products().skip(dotPosition).collect(productString()) + COLON_STRING;
  }

  private Collector<Product, ?, String> productString() {
    return mapping(p -> p.toString(), joining(" "));
  }
}
