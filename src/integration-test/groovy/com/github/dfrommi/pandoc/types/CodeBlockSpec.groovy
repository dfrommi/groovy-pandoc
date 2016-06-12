package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class CodeBlockSpec extends TypeSpecBase {
  @Shared
  def conversions = [

    ['''
```{#mycode .haskell .numberLines startFrom="100" endAt="200"}
Some code
```
''',
     new CodeBlock(code: "Some code", attr: new Attributes(identifier: "mycode", classes: ["haskell", "numberLines"],
       properties: [startFrom: "100", endAt: "200"]))
    ]

  ]
}
