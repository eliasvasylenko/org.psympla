package org.psympla.grammar;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.psympla.pattern.Patterns.literal;
import static org.psympla.pattern.Patterns.sequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.constraint.Constraint;
import org.psympla.constraint.Scope;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Sequence;
import org.psympla.symbol.Symbol;

public class Rule {
  private final Pattern pattern;
  private final List<Pattern> products;
  private final Scope scope;

  public Rule(Pattern pattern) {
    this(pattern, List.of(), Scope.empty());
  }

  public Rule(LexicalItem patternLiteral) {
    this(literal(patternLiteral));
  }

  public Rule(Symbol symbol, Pattern parameter) {
    this(sequence(literal(symbol), parameter));
  }

  public Rule(Symbol symbol, LexicalItem parameterLiteral) {
    this(Sequence.of(symbol, parameterLiteral));
  }

  protected Rule(Pattern pattern, List<Pattern> products, Scope scope) {
    this.pattern = pattern;
    this.products = List.copyOf(products);
    this.scope = scope;
  }

  public Pattern pattern() {
    return pattern;
  }

  public Stream<Pattern> products() {
    return products.stream();
  }

  public int length() {
    return products.size();
  }

  public Pattern product(int index) {
    return products.get(index);
  }

  public Scope scope() {
    return scope;
  }

  public Rule withProduct(LexicalItem product) {
    return withProducts(product);
  }

  public final Rule withProducts(LexicalItem... production) {
    return withProducts(Stream.of(production).map(Patterns::literal).collect(toList()));
  }

  public Rule withProduct(Pattern product) {
    return withProducts(product);
  }

  public final Rule withProducts(Pattern... production) {
    return withProducts(List.of(production));
  }

  public Rule withProducts(Collection<? extends Pattern> production) {
    List<Pattern> newProduction = new ArrayList<>(this.products.size() + production.size());
    newProduction.addAll(this.products);
    newProduction.addAll(production);
    return new Rule(pattern, newProduction, scope);
  }

  public Rule withConstraint(Constraint constraint) {
    return new Rule(pattern, products, scope.withConstraint(constraint));
  }

  @Override
  public String toString() {
    return pattern + " -> " + products.stream().map(Object::toString).collect(joining(" ")) + " : "
        + scope;
  }
}
