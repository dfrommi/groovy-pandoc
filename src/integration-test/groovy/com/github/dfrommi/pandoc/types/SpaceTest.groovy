package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class SpaceTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Text with spaces', new Space()] as Object[]
		]
	}
}
