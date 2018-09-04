# Psympla Parser
Psympla, pronounced "simpler", is an efficient parser generator for context sensitive grammars. It is able to generate parsers and deparsers for grammars of **p**arametric **sy**mbol **m**eta**p**attern **la**nguages. These are grammars with the following features:

- Symbols are parametric, allowing us to encode context.
- Rules are applied to symbols via pattern matching, allowing us to extract features of context.
- Time complexity for parsing context-free grammars is equivalent to the Earley algorithm (with a few optimisations).
- Grammar definition is decoupled from user code and data structures, with no intermediate AST step.

Take the following example for a grammar of a list of known people's names, using a variation on typical BNF style notation:

```
person-list    ->  '[' repeat(person) ']'
repeat(T)      ->  '' | T repeat(T)
person         ->  'alice' | 'bob' | 'carol' | 'dan'
```

Here `person-list` is our start symbol, and we assume a lexer step to separate tokens by whitespace. This will recognise e.g. `[ alice alice alice ]`, `[ bob ]`, or `[ bob carol dan ]`, but not `[ craig ]` or `dave`.

The interesting part here is the symbol `repeat<name>`, which is a parametric symbol named `repeat`, parameterized with the argument `person`. This is then matched against the pattern on the left-hand side of the `repeat<T>` rule, which instantiates the parameter `T` with the argument `person` and constructs the production `'' | name repeat<person>`.

We may also employ more sophisticated structural pattern matching to extract nested features of parameterized symbols, as in the following example of a grammer for the typical context sensitive language { a<sup>n</sup>b<sup>n</sup>c<sup>n</sup> : n ≥ 1 }:

```
1:  start              ->  repeat('a' I) repeat('b' I) repeat('c' I)
2:  repeat(T I>0)      ->  repeat(T I--) T
3:  repeat(T 0)        ->  empty
```

Where each production numbered.

This matches the phrase `aabbcc` with the following sequence of derivations:

```
initial:   start
apply 1: repeat('a' 2) repeat('b' 2) repeat('c' 2)
apply 2: repeat('a' 1) 'a' repeat('b' 1) 'b' repeat('c' 1) 'c'
apply 2: repeat('a' 0) 'aa' repeat('b' 0) 'bb' repeat('c' 0) 'cc'
apply 3: 'aabbcc'
```

This can trivially be extended to a language with any number of equally-sized sequences of letters by appending more terms to the start rule.
