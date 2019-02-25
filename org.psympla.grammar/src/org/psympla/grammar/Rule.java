package org.psympla.grammar;

import static org.psympla.pattern.Patterns.cons;
import static org.psympla.pattern.Patterns.literal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.psympla.constraint.Constraint;
import org.psympla.constraint.Scope;
import org.psympla.pattern.Pattern;
import org.psympla.pattern.Patterns;
import org.psympla.symbol.LexicalItem;
import org.psympla.symbol.Symbol;

public class Rule {
  private final Symbol symbol;
  private final Pattern parameter;
  private final List<Pattern> products;
  private final Scope scope;

  public Rule(Symbol symbol) {
    this(symbol, Patterns.nil());
  }

  public Rule(Symbol symbol, LexicalItem parameter) {
    this(symbol, literal(parameter));
  }

  public Rule(Symbol symbol, Pattern parameter) {
    this(symbol, parameter, List.of(), Scope.empty());
  }

  private Rule(Symbol symbol, Pattern parameter, List<Pattern> production, Scope scope) {
    this.symbol = symbol;
    this.parameter = parameter;
    this.products = List.copyOf(production);
    this.scope = scope;
  }

  public Symbol symbol() {
    return symbol;
  }

  public Pattern parameter() {
    return parameter;
  }

  public Pattern pattern() {
    return cons(symbol, parameter);
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

  public Rule withProduct(Symbol productSymbol) {
    return withProduct(productSymbol, Patterns.wildcard());
  }

  public Rule withProduct(Symbol productSymbol, Pattern productParameter) {
    return withProduct(cons(productSymbol, productParameter));
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
    return new Rule(symbol, parameter, newProduction, scope);
  }

  public Rule withConstraint(Constraint constraint) {
    return new Rule(symbol, parameter, products, scope.withConstraint(constraint));
  }
}
