package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class ParaTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Text',
			 new Para(content: new Str("Text"))] as Object[]
		]
	}
}
