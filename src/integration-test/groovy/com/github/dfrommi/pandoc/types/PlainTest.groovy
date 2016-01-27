package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters;

class PlainTest extends TypeTestBase {
	@Parameters
	static getParams() { [
		['* PlainText',
			new Plain(content: [new Str("PlainText")])
		] as Object[]
	] }

}
