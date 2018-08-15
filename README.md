# Psympl
Psympl, pronounced as "simple", is a parser generator for context sensitive grammars. The name comes from **p**arametric **sy**mbol **m**eta**p**attern **l**anguage, which sounds complicated, but once demystified is quite straightforward to understand for users already familiar with typical generative grammars and production rules.

"Parametric symbol" refers to the fact that symbols may be parameterized with other symbols. And if we describe a production rule as the expansion of a symbol into a *pattern* of symbols, then "*meta*pattern" refers to the concept of constructing our production rules by pattern-matching against a parameterized symbol.

This is easier to understand with an example, using a recognisable variation on BNF notation (and assuming a lexing step to separate tokens):

```
name-list     = '[' repeat<name> ']'
repeat<T>     = '' | T repeat<T>
name          = 'alice' | 'bob' | 'carol' | 'dan'
```

Which with `name-list` as our start symbol will recognise e.g. `[ alice alice alice ]`, `[ bob ]`, or `[ bob carol dan ]`, but not `[ craig ]` or `dave`.

The interesting part here is the symbol `repeat<name>`, which is a parametric symbol named `repeat`, parameterized with the argument `name`. This is then matched against the pattern on the left-hand side of the `repeat<T>` rule, which instantiates the parameter `T` with the argument `name` and constructs the production `'' | name repeat<name>`.

We may also employ more sophisticated structural pattern matching to extract nested features of parameterized symbols, as in the following example of a grammer for the typical context sensitive language a^n b^n c^n d^n:

```
s             = g<''>
g<T>          = g<g<T>>
              | a<T> b<T> c<T> d<T>
a<g<T>>       = 'a' a<T>
b<g<T>>       = 'b' a<T>
c<g<T>>       = 'c' a<T>
d<g<T>>       = 'd' a<T>
_<''>         = ''                  # note, this also matches directly against the g<''> production of s!
```

Which could also be refactored as follows:

```
s             = g<''>
g<T>          = g<g<T>>
              | e<T 'a'> e<T 'b'> e<T 'c'> e<T 'd'>
e<g<T> U>     = e<T U> U
e<'' _>       = ''
```
