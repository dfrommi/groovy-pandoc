package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class HorizontalRuleTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['* * * * *', new HorizontalRule()] as Object[],
			['--------', new HorizontalRule()] as Object[],
		]
	}
}
