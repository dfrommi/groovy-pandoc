package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class RawInlineTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Some text and then <svg> element',
			 new RawInline(format: "html", content: "<svg>")] as Object[]
		]
	}

}
