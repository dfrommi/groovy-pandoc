package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class StrTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['TheText',
			 new Str("TheText")] as Object[]
		]
	}

}
