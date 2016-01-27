package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class SuperscriptTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['2^10^ is 1024.',
			 new Superscript(text: [new Str("10")])] as Object[]
		]
	}
}
