package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters;

class DefinitionListTest extends TypeTestBase {

	@Parameters
	static getParams() { [
		['''
head
  ~ value
''',
				new DefinitionList(items: [
					new DefinitionItem(term: [new Str("head")], definitions: [
						new Plain(content: [new Str("value")])
					])
				])
		] as Object[]
	] }
}
