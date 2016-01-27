package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class SmallCapsTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Here are <span style="font-variant:small-caps;">smallcaps</span>',
			 new SmallCaps(text: [new Str("smallcaps")])] as Object[]
		]
	}
}
