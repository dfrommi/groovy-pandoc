package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class StrongTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['The Text is **strong**.',
			 new Strong(text: [new Str("strong")])] as Object[],
		 	['The Text is __strong__.',
			 new Strong(text: [new Str("strong")])] as Object[]
		]
	}

}
