package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class CodeBlockTest extends TypeTestBase {
	@Parameters
	static getParams() { [
		['''
```{#mycode .haskell .numberLines startFrom="100" endAt="200"}
Some code
```
''',
		 new CodeBlock(code: "Some code", attr: new Attributes(identifier: "mycode", classes: ["haskell", "numberLines"],
			properties: [startFrom: "100", endAt: "200"]))] as Object[]
	] }
}
