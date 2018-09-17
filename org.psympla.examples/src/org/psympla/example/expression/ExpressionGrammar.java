package org.psympla.example.expression;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.psympla.example.expression.ExpressionLexicon.NAMESPACE;

import java.util.List;

import org.psympla.grammar.Grammar;
import org.psympla.grammar.Production;
import org.psympla.grammar.Rule;
import org.psympla.lexicon.LexicalClass;
import org.psympla.symbol.Symbol;
import org.psympla.symbol.Variable;

public class ExpressionGrammar extends Grammar {
  public static final Symbol EXPRESSION = new Symbol(NAMESPACE, "expression");
  public static final Symbol ADD = new Symbol(NAMESPACE, "add");
  public static final Symbol SUBTRACT = new Symbol(NAMESPACE, "subtract");
  public static final Symbol MULTIPLY = new Symbol(NAMESPACE, "multiply");
  public static final Symbol DIVIDE = new Symbol(NAMESPACE, "divide");
  public static final Symbol PARENTHESES = new Symbol(NAMESPACE, "parentheses");

  protected ExpressionGrammar(ExpressionLexicon lexicon) {
    super(rules(lexicon), lexicon.getLexicalClasses().map(LexicalClass::symbol).collect(toList()));
  }

  private static List<Rule> rules(ExpressionLexicon lexicon) {
    return asList(

        new Rule(EXPRESSION, new Production(ADD)),
        new Rule(EXPRESSION, new Production(SUBTRACT)),
        new Rule(EXPRESSION, new Production(MULTIPLY)),
        new Rule(EXPRESSION, new Production(DIVIDE)),
        new Rule(EXPRESSION, new Production(PARENTHESES)),
        new Rule(EXPRESSION, new Production(lexicon.variable().instance(new Variable<>("V")))),

        new Rule(ADD, new Production(EXPRESSION, lexicon.operator().instance("+"), EXPRESSION)),

        new Rule(
            SUBTRACT,
            new Production(EXPRESSION, lexicon.operator().instance("-"), EXPRESSION)),

        new Rule(
            MULTIPLY,
            new Production(EXPRESSION, lexicon.operator().instance("*"), EXPRESSION)),

        new Rule(DIVIDE, new Production(EXPRESSION, lexicon.operator().instance("/"), EXPRESSION)),

        new Rule(
            PARENTHESES,
            new Production(
                lexicon.operator().instance("("),
                EXPRESSION,
                lexicon.operator().instance(")")))

    );
  }
}
