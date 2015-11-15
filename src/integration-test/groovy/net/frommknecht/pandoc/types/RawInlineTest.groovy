package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class RawInlineTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Some text and then <svg> element',
			 new RawInline(format: "html", content: "<svg>")] as Object[]
		]
	}

}
