# Groovy template processing

This file contains an `include-gtp` code block, which passes each file referenced in the block through a simple groovy template engine. To use it, make sure the groovy-pandoc jar file is in your path and then:

`pandoc -f markdown -t markdown --filter ./IncludeGroovyTemplate.groovy example_gt_include.md`

```include-gtp
mygroovytemplate.gt
```
