package org.psympla.parser.earley.products;

import static java.util.stream.Collectors.toSet;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.psympla.constraint.Constraint;
import org.psympla.grammar.Rule;
import org.psympla.pattern.Pattern;

public class Product {
  private final Pattern pattern;
  private final Set<Constraint> constraints;

  private Product(Pattern pattern, Set<Constraint> constraints) {
    this.pattern = pattern;
    this.constraints = constraints;
  }

  public static Stream<Product> getProducts(Rule rule) {
    return rule
        .products()
        .map(product -> new Product(product, rule.scope().constraints().collect(toSet())));
  }

  public Pattern pattern() {
    return pattern;
  }

  public Set<Constraint> constraints() {
    return constraints;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;

    Product that = (Product) obj;

    return Objects.equals(this.pattern, that.pattern)
        && Objects.equals(this.constraints, that.constraints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pattern, constraints);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + pattern + ", " + constraints + ")";
  }
}