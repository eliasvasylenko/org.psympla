package org.psympla.language.earley.index;

import static java.util.stream.Collectors.joining;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

//TODO value type & record
public class LR0Item {
  private final IndexedRule rule;
  private final int dotPosition;

  public LR0Item(IndexedRule rule, int dotPosition) {
    this.rule = rule;
    this.dotPosition = dotPosition;
  }

  public IndexedRule rule() {
    return rule;
  }

  public int dotPosition() {
    return dotPosition;
  }

  public boolean isComplete() {
    return rule.productCount() == dotPosition;
  }

  public Optional<IndexedProduct> nextProduct() {
    return isComplete() ? Optional.empty() : Optional.of(rule.product(dotPosition));
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
    return rule.pattern() + " -> " + toString(rule.products().limit(dotPosition)) + " . "
        + toString(rule.products().skip(dotPosition));
  }

  private String toString(Stream<IndexedProduct> products) {
    return products.map(Object::toString).collect(joining(" "));
  }
}
