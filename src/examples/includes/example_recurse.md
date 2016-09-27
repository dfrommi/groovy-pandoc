# My Header

Below this is an include block. Every file listed in the include block will be included inline and then run through the include filter 3 times, allowing for 3 levels of inclusion.

To run it, make sure the groovy-pandoc jar is in your path and then: `pandoc -f markdown -t markdown --filter ./IncludeRecurse.groovy example_recurse.md`

```include
firstinclude.md
```
