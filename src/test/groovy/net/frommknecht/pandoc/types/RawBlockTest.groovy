package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

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
