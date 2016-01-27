package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class RawBlockTest extends TypeTestBase {
	@Parameters
	static getParams() { [
			['''
Some text 
			
<svg>text</svg>

''',
			 new RawBlock(format: "html", content: "<svg>")] as Object[]
		]
	}
}
