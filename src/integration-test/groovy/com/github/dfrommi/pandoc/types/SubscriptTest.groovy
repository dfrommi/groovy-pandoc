package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class SubscriptTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['H~2~O is water.',
			 new Subscript(text: [new Str("2")])] as Object[]
		]
	}
}
