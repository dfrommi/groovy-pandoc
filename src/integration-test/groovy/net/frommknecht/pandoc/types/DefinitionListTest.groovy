package net.frommknecht.pandoc.types;

import static org.junit.Assert.*;

import org.junit.Test;
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
